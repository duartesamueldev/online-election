package com.duarte.online_election_api.business.enums;

public enum CandidateStatus {
    WAITING, //Waiting for result
    DISQUALIFIED, //Was disqualified for not meeting requirements
    WITHDRAWN, //Withdrew candidacy
    ELECTED //Won the election
}
