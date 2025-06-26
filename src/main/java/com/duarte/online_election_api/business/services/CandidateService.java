package com.duarte.online_election_api.business.services;

import com.duarte.online_election_api.adapters.dtos.CandidateDTO;
import com.duarte.online_election_api.business.enums.Gender;
import com.duarte.online_election_api.business.enums.Nationality;
import com.duarte.online_election_api.business.enums.PoliticalParty;
import com.duarte.online_election_api.business.enums.Position;
import com.duarte.online_election_api.business.exceptions.CandidateException;
import com.duarte.online_election_api.business.rules.CandidateRules;
import com.duarte.online_election_api.infrastucture.entity.Candidate;
import com.duarte.online_election_api.infrastucture.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateRules candidateRules;

    public CandidateService(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
        this.candidateRules = new CandidateRules(candidateRepository);
    }

    public void saveCandidate(CandidateDTO dto){

        candidateRules.validateCandidate(dto);

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
