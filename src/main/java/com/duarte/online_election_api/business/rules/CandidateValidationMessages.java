package com.duarte.online_election_api.business.rules;

import com.duarte.online_election_api.business.enums.Position;

public class CandidateValidationMessages {

    public static final String CPF_DUPLICATE = "CPF already registered";
    public static final String NUMBER_DUPLICATE = "Number already belongs to a candidate";
    public static final String NOT_BRAZILIAN = "Doesn't have Brazilian nationality";
    public static final String TOO_YOUNG = "Age under 18 years";

    public static String minimumAgeMessage(long age, Position position){
        return "Candidate must be at least " + age + " years old for position " + position;
    }

    private CandidateValidationMessages(){

    }
}
