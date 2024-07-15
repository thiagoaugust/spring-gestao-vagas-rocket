package br.com.thiago.gestaovagas.modules.candidate.controllers;

import br.com.thiago.gestaovagas.modules.candidate.CandidateEntity;
import br.com.thiago.gestaovagas.modules.candidate.usecases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CandidateEntity>> findAll(){
        //List<CandidateEntity> cadidates = candidateRepository.findAll();
        return ResponseEntity.ok(null);
    }
}
