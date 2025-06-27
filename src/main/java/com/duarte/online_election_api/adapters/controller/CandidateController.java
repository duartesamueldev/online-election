package com.duarte.online_election_api.adapters.controller;

import com.duarte.online_election_api.adapters.dtos.CandidateDTO;
import com.duarte.online_election_api.adapters.dtos.CandidateResponseDTO;
import com.duarte.online_election_api.business.services.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService){
        this.candidateService = candidateService;
    }

    @PostMapping
    public ResponseEntity<Void> saveCandidate(@RequestBody @Valid CandidateDTO request){
        candidateService.saveCandidate(request);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<CandidateResponseDTO> findByNumber(@RequestParam("number") Integer number){
        var response = candidateService.findByNumber(number);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
