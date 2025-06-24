package com.duarte.online_election_api.business.services;

import com.duarte.online_election_api.infrastucture.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;
}
