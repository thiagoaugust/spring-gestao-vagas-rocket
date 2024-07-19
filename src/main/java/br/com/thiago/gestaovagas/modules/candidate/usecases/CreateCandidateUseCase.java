package br.com.thiago.gestaovagas.modules.candidate.usecases;

import br.com.thiago.gestaovagas.exceptions.AlreadyExistUserException;
import br.com.thiago.gestaovagas.modules.candidate.CandidateEntity;
import br.com.thiago.gestaovagas.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CandidateRepository candidateRepository;

    public void execute(CandidateEntity candidateEntity){
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new AlreadyExistUserException();
                });
        var passwordEncoded = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(passwordEncoded);
        this.candidateRepository.save(candidateEntity);
        System.out.println("Salvo com sucesso");
    }
}
