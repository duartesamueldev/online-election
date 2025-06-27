package com.duarte.online_election_api.business.services;

import com.duarte.online_election_api.adapters.dtos.CandidateDTO;
import com.duarte.online_election_api.adapters.dtos.CandidateResponseDTO;
import com.duarte.online_election_api.adapters.dtos.CandidateValidationResult;
import com.duarte.online_election_api.business.enums.Gender;
import com.duarte.online_election_api.business.enums.Nationality;
import com.duarte.online_election_api.business.enums.PoliticalParty;
import com.duarte.online_election_api.business.enums.Position;
import com.duarte.online_election_api.business.exceptions.CandidateException;
import com.duarte.online_election_api.business.exceptions.DataNotFound;
import com.duarte.online_election_api.business.rules.CandidateRules;
import com.duarte.online_election_api.business.rules.CandidateValidationMessages;
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

        CandidateValidationResult result = candidateRules.validateAndGetStatus(dto);

        if(candidateRepository.existsByCpf(dto.cpf())){
            throw new CandidateException(CandidateValidationMessages.CPF_DUPLICATE);
        }

        if(candidateRepository.existsByNumber(Integer.parseInt(dto.number()))){
            throw new CandidateException(CandidateValidationMessages.NUMBER_DUPLICATE);
        }

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
                .candidateStatus(result.candidateStatus())
                .ineligibilityReason(result.reason())
                .build();

        candidateRepository.saveAndFlush(newCandidate);
    }

    public CandidateResponseDTO findByNumber(Integer number){
        return new CandidateResponseDTO(candidateRepository.findByNumber(number)
                .orElseThrow(() -> new DataNotFound(number + " is not associated with any candidate.")));
    }
}
