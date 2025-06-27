package com.duarte.online_election_api.business.rules;

import com.duarte.online_election_api.adapters.dtos.CandidateDTO;
import com.duarte.online_election_api.adapters.dtos.CandidateValidationResult;
import com.duarte.online_election_api.business.enums.CandidateStatus;
import com.duarte.online_election_api.business.enums.Nationality;
import com.duarte.online_election_api.business.enums.Position;
import com.duarte.online_election_api.business.exceptions.CandidateException;
import com.duarte.online_election_api.infrastucture.entity.Candidate;
import com.duarte.online_election_api.infrastucture.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class CandidateRules {

    private final CandidateRepository candidateRepository;

    public final Map<Position, Integer> MINIMUM_AGE_BY_POSITION = Map.of(
            Position.COUNCILOR, 18,
            Position.MAYOR, 21,
            Position.STATE_DEPUTY, 21,
            Position.FEDERAL_DEPUTY, 21,
            Position.GOVERNOR, 30,
            Position.SENATOR, 35,
            Position.PRESIDENT, 35
    );

    public CandidateValidationResult validateAndGetStatus(CandidateDTO dto){
        Position position = Position.valueOf(dto.position().toUpperCase());
        Nationality nationality = Nationality.valueOf(dto.nationality().toUpperCase());
        LocalDate birthDate = LocalDate.parse(dto.birthDate());
        long age = ChronoUnit.YEARS.between(birthDate, LocalDate.now());

        List<String> reasons = new ArrayList<>();

        if(age < 18){
            return new CandidateValidationResult(CandidateStatus.DISQUALIFIED,
                    CandidateValidationMessages.TOO_YOUNG);
        }

        int requiredAge = MINIMUM_AGE_BY_POSITION.get(position);
        if(age < requiredAge){
            reasons.add(CandidateValidationMessages.minimumAgeMessage(requiredAge, position));
        }

        if((position == Position.PRESIDENT || position == Position.SENATOR) && nationality != Nationality.BRAZILIAN){
           reasons.add(CandidateValidationMessages.NOT_BRAZILIAN);
        }

        if(!reasons.isEmpty()){
            return new CandidateValidationResult(CandidateStatus.DISQUALIFIED, String.join("; ", reasons));
        }

        return new CandidateValidationResult(CandidateStatus.WAITING, null);
    }

}
