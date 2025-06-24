package com.duarte.online_election_api.adapters.dtos;

import com.duarte.online_election_api.business.enums.Gender;
import com.duarte.online_election_api.business.enums.Nationality;
import com.duarte.online_election_api.business.enums.PoliticalParty;
import com.duarte.online_election_api.business.enums.Position;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CandidateDTO(
        @NotBlank(message = "Full name is required")
        String fullName,
        @NotBlank(message = "CPF is required")
        String cpf,
        @NotNull(message = "Candidate number is required.")
        @Min(value = 10, message = "Candidate number must be a 2-digit number, ex: 10, 11, 22")
        @Max(value = 99, message = "Candidate number must be a 2-digit number, ex: 11, 22, 13")
        Integer number,
        @NotNull(message = "State is required")
        @Size(min = 2, max = 2, message = "State must be informed by the acronym, Ex: RJ")
        String state,
        @NotNull(message = "Birth date is required")
        @Past(message = "Birth date must be in the past.")
        LocalDate birthDate,
        @NotNull(message = "Gender is required")
        Gender gender,
        @NotNull(message = "nationality is required")
        Nationality nationality,
        @NotNull(message = "Political party is required")
        PoliticalParty politicalParty,
        @NotNull(message = "Position is required")
        Position position
) {
}
