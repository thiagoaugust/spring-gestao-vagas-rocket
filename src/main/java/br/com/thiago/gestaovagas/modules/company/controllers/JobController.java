package br.com.thiago.gestaovagas.modules.company.controllers;

import br.com.thiago.gestaovagas.modules.company.entities.JobEntity;
import br.com.thiago.gestaovagas.modules.company.usecases.CreateJobUserCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUserCase createJobUserCase;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody JobEntity jobEntity, HttpServletRequest request){
        var companyId = request.getAttribute("company_id");
        jobEntity.setCompanyId(UUID.fromString(companyId.toString()));
        this.createJobUserCase.execute(jobEntity);
        return ResponseEntity.ok().build();
    }
}
