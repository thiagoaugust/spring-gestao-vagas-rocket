package br.com.thiago.gestaovagas.modules.company.usecases;

import br.com.thiago.gestaovagas.exceptions.AlreadyExistUserException;
import br.com.thiago.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.thiago.gestaovagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public void execute(CompanyEntity companyEntity){
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new AlreadyExistUserException();
                });
        this.companyRepository.save(companyEntity);
    }
}
