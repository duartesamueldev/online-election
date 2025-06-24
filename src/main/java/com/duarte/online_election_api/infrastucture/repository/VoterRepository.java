package com.duarte.online_election_api.infrastucture.repository;

import com.duarte.online_election_api.infrastucture.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends JpaRepository<Long, Voter> {
}
