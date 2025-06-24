package com.duarte.online_election_api.infrastucture.entity;

import com.duarte.online_election_api.business.enums.Gender;
import com.duarte.online_election_api.business.enums.PoliticalParty;
import com.duarte.online_election_api.business.enums.Position;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_candidates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "candidate_number", unique = true, nullable = false)
    private Integer number;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "political_party", nullable = false)
    private PoliticalParty politicalParty;

    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    private Position position;

}
