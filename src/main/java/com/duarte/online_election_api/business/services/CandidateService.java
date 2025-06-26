package com.duarte.online_election_api.business.services;

import com.duarte.online_election_api.adapters.dtos.CandidateDTO;
import com.duarte.online_election_api.business.enums.Gender;
import com.duarte.online_election_api.business.enums.Nationality;
import com.duarte.online_election_api.business.enums.PoliticalParty;
import com.duarte.online_election_api.business.enums.Position;
import com.duarte.online_election_api.infrastucture.entity.Candidate;
import com.duarte.online_election_api.infrastucture.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    public void saveCandidate(CandidateDTO dto){

        Candidate newCandidate = Candidate.builder()
                .fullName(dto.fullName())
                .cpf(dto.cpf())
                .number(Integer.parseInt(dto.number()))
                .state(dto.state().toUpperCase())
                .birthDate(LocalDate.parse(dto.birthDate()))
                .gender(Gender.valueOf(dto.gender().toUpperCase()))
                .nationality(Nationality.valueOf(dto.nationality().toUpperCase()))
                .politicalParty(PoliticalParty.valueOf(dto.politicalParty().toUpperCase()))
                .position(Position.valueOf(dto.position().toUpperCase()))
                .build();

        candidateRepository.saveAndFlush(newCandidate);
    }
}
