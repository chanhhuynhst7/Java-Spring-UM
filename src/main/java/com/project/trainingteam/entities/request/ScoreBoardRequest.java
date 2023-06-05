package com.project.trainingteam.entities.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="um_score_board_request")
public class ScoreBoardRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_board_request_id")
    private Long id;

    private Long letterId;

    private Long letterTypeId;

    private String letterTypeName;

    private String scoreBoardTypeName;

    private Integer scoreBoardTypeQuantity = 0;

    private Integer scoreBoardTypePrice = 3000;

    private String scoreBoardTypeDesc = "";

}
