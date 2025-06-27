package com.duarte.online_election_api.business.exceptions;

public class DataNotFound extends RuntimeException {
    public DataNotFound(String message) {
        super(message);
    }
}
