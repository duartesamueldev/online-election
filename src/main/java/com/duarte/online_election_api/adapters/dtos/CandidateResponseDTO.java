package com.duarte.online_election_api.adapters.dtos;

import com.duarte.online_election_api.business.enums.PoliticalParty;
import com.duarte.online_election_api.business.enums.Position;
import com.duarte.online_election_api.infrastucture.entity.Candidate;

public record CandidateResponseDTO(
        String fullName,
        Integer number,
        PoliticalParty politicalParty,
        Position position
){
    public CandidateResponseDTO(Candidate entity){
        this(entity.getFullName(), entity.getNumber(), entity.getPoliticalParty(), entity.getPosition());
    }
}
