package com.duarte.online_election_api.infrastucture.repository;

import com.duarte.online_election_api.infrastucture.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Long, Candidate> {
}
