package com.duarte.online_election_api.adapters.dtos;

import com.duarte.online_election_api.business.enums.Gender;
import com.duarte.online_election_api.business.enums.Nationality;
import com.duarte.online_election_api.business.enums.PoliticalParty;
import com.duarte.online_election_api.business.enums.Position;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.loader.ast.spi.NaturalIdLoadOptions;

import java.time.LocalDate;

public record CandidateDTO(
        @NotBlank(message = "Full name is required")
        String fullName,
        @NotBlank(message = "CPF is required")
        String cpf,
        @NotNull(message = "Candidate number is required.")
        @Size(min = 2, max = 2, message = "Candidate number must contain only 2 digits. Ex: 11, 22, 13")
        Integer number,
        @NotBlank(message = "State is required")
        @Size(min = 2, max = 2, message = "State must be informed by the acronym, Ex: RJ")
        String state,
        @NotNull(message = "Birth date is required")
        @Past(message = "Birth date must be in the past.")
        LocalDate birthDate,
        @NotBlank(message = "Gender is required")
        Gender gender,
        @NotBlank(message = "nationality is required")
        Nationality nationality,
        @NotBlank(message = "Political party is required")
        PoliticalParty politicalParty,
        @NotBlank(message = "Position is required")
        Position position
) {
}
