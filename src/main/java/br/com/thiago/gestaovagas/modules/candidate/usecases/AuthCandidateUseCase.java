package br.com.thiago.gestaovagas.modules.candidate.usecases;

import br.com.thiago.gestaovagas.modules.candidate.CandidateRepository;
import br.com.thiago.gestaovagas.modules.candidate.dto.AuthCandidateRequest;
import br.com.thiago.gestaovagas.modules.candidate.dto.AuthCandidateResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    public AuthCandidateResponse execute(AuthCandidateRequest authCandidateRequest) throws AuthenticationException {
        var candidate = candidateRepository.findByUsername(authCandidateRequest.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));
        var matches = passwordEncoder.matches(authCandidateRequest.password(), candidate.getPassword());
        if(!matches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", List.of("candidate"))
                .withExpiresAt(Instant.now().plus(Duration.ofSeconds(30)))
                .sign(algorithm);

        AuthCandidateResponse authCandidateResponse = AuthCandidateResponse.builder().access_token(token).build();
        return authCandidateResponse;
    }

}
