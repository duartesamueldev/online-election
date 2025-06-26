package com.duarte.online_election_api.adapters.dtos;

import com.duarte.online_election_api.business.enums.Gender;
import com.duarte.online_election_api.business.enums.Nationality;
import com.duarte.online_election_api.business.enums.PoliticalParty;
import com.duarte.online_election_api.business.enums.Position;
import jakarta.validation.constraints.*;


public record CandidateDTO(
        @NotBlank(message = "Full name is required")
        String fullName,

        @NotBlank(message = "CPF is required")
        @Size(min = 11, max = 11, message = "CPF must contain exactly 11 digits. Only numbers")
        String cpf,

        @NotBlank(message = "Number is required")
        @Size(min = 2, max = 2, message = "Candidate number must be a 2-digit number")
        String number,

        @NotNull(message = "State is required")
        @Size(min = 2, max = 2, message = "State must be informed by the acronym, Ex: RJ")
        String state,

        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",
                message = "Birth date must be in format yyyy-MM-dd")
        String birthDate,

        @NotBlank(message = "Gender is required")
        @Pattern(regexp = "MALE|FEMALE|OTHER",
                message = "Gender must be one of: Male, Female, Other",
                flags = Pattern.Flag.CASE_INSENSITIVE
        )
        String gender,

        @NotBlank(message = "nationality is required")
        @Pattern(regexp = "BRAZILIAN|FOREIGNER",
                message = "Nationality must be one of: Brazilian , Foreigner",
                flags = Pattern.Flag.CASE_INSENSITIVE
        )
        String nationality,

        @NotBlank(message = "Political party is required")
        @Pattern(regexp = "PT|PL|PSDB|MDB|PSD|PSOL|PDT",
                message = "Political party must be one of: PT, PL, PSDB, MDB, PSD, PSOL, PDT",
                flags = Pattern.Flag.CASE_INSENSITIVE
        )
        String politicalParty,

        @NotBlank(message = "Position is required")
        @Pattern(regexp = "PRESIDENT|GOVERNOR|MAYOR|FEDERAL_DEPUTY|STATE_DEPUTY|SENATOR|COUNCILOR",
                message = "Position must be one of: President, Governor, Mayor, Federal_Deputy, State_Deputy, Senator, Councilor",
                flags = Pattern.Flag.CASE_INSENSITIVE
        )
        String position
) {
}
