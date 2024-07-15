package br.com.thiago.gestaovagas.modules.company.controllers;

import br.com.thiago.gestaovagas.modules.company.entities.JobEntity;
import br.com.thiago.gestaovagas.modules.company.usecases.CreateJobUserCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUserCase createJobUserCase;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody JobEntity jobEntity){
        this.createJobUserCase.execute(jobEntity);
        return ResponseEntity.ok().build();
    }
}
