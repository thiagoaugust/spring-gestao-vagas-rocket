package br.com.thiago.gestaovagas.modules.company.usecases;

import br.com.thiago.gestaovagas.exceptions.AlreadyExistUserException;
import br.com.thiago.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.thiago.gestaovagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(CompanyEntity companyEntity){
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new AlreadyExistUserException();
                });
        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);
        this.companyRepository.save(companyEntity);
    }
}
