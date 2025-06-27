package com.duarte.online_election_api.adapters.dtos;

import com.duarte.online_election_api.business.enums.CandidateStatus;

public record CandidateValidationResult(CandidateStatus candidateStatus,
                                        String reason) {
}
