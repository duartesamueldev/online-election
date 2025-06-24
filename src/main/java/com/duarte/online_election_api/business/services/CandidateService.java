package com.duarte.online_election_api.business.services;

import com.duarte.online_election_api.adapters.dtos.CandidateDTO;
import com.duarte.online_election_api.infrastucture.entity.Candidate;
import com.duarte.online_election_api.infrastucture.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    public void saveCandidate(CandidateDTO dto){

        //Handle exceptions
        Candidate newCandidate = Candidate.builder()
                .fullName(dto.fullName())
                .cpf(dto.cpf())
                .number(dto.number())
                .state(dto.state())
                .birthDate(dto.birthDate())
                .gender(dto.gender())
                .nationality(dto.nationality())
                .politicalParty(dto.politicalParty())
                .position(dto.position())
                .build();

        candidateRepository.saveAndFlush(newCandidate);
    }
}
