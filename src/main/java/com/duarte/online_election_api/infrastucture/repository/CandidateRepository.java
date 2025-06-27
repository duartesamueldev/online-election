package com.duarte.online_election_api.infrastucture.repository;

import com.duarte.online_election_api.infrastucture.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByNumber(Integer number);
    Optional<Candidate> findByNumber(Integer number);
}
