package br.com.thiago.gestaovagas.modules.company.usecases;

import br.com.thiago.gestaovagas.modules.company.dto.AuthCompanyDto;
import br.com.thiago.gestaovagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDto authCompanyDto) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDto.getUsername())
                .orElseThrow(()->{
                    throw new UsernameNotFoundException("Company not found");
                });
        var matches = this.passwordEncoder.matches(authCompanyDto.getPassword(), company.getPassword());
        if(!matches){
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create().withIssuer("javagas")
                .withSubject(company.getId().toString())
                .sign(algorithm);
    }

}
