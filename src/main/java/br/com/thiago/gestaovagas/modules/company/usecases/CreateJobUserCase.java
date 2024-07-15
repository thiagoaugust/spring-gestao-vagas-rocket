package br.com.thiago.gestaovagas.modules.company.usecases;

import br.com.thiago.gestaovagas.modules.company.entities.JobEntity;
import br.com.thiago.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUserCase {

    @Autowired
    private JobRepository jobRepository;

    public void execute(JobEntity jobEntity) {
        this.jobRepository.save(jobEntity);
    }
}
