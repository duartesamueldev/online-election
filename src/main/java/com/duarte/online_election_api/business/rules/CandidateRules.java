package com.duarte.online_election_api.business.rules;

import com.duarte.online_election_api.adapters.dtos.CandidateDTO;
import com.duarte.online_election_api.business.enums.Nationality;
import com.duarte.online_election_api.business.enums.Position;
import com.duarte.online_election_api.business.exceptions.CandidateException;
import com.duarte.online_election_api.infrastucture.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    public void validateCandidate(CandidateDTO dto){

        Position position = Position.valueOf(dto.position().toUpperCase());
        Nationality nationality = Nationality.valueOf(dto.nationality().toUpperCase());
        LocalDate birthDate = LocalDate.parse(dto.birthDate());
        long age = ChronoUnit.YEARS.between(birthDate, LocalDate.now());

        if(age < 18){
            throw new CandidateException("You are not old enough to run for any political office.");
        }

        switch(position){
            case PRESIDENT, SENATOR -> {
                int requiredAge = MINIMUM_AGE_BY_POSITION.get(position);
                if(age < requiredAge){
                    throw new CandidateException("Candidate must be at least " + requiredAge + " years old for position: " + position);
                }
                if(nationality != Nationality.BRAZILIAN){
                    throw new CandidateException("Candidate must be brazilian for position: " + position);
                }
            }

            case GOVERNOR -> {
                int requiredAge = MINIMUM_AGE_BY_POSITION.get(position);
                if (age < requiredAge){
                    throw new CandidateException("Candidate must be at least " + requiredAge + " years old for position: " + position);
                }
            }

            case MAYOR, STATE_DEPUTY, FEDERAL_DEPUTY, COUNCILOR -> {
                int requiredAge = MINIMUM_AGE_BY_POSITION.get(position);
                if(age < requiredAge){
                    throw new CandidateException("Candidate must be a least " + requiredAge + " years old for position: " + position);
                }
            }

            default -> throw new CandidateException("Position not recognized or not supported.");

        }

        if(candidateRepository.existsByCpf(dto.cpf())){
            throw new CandidateException("Cpf already registered.");
        }

        if(candidateRepository.existsByNumber(Integer.parseInt(dto.number()))){
            throw new CandidateException("The number " + dto.number() + " already belongs to a candidate");
        }
    }

}
