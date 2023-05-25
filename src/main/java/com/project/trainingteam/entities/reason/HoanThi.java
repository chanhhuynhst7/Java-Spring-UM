package com.project.trainingteam.entities.reason;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="um_reason_hoan_thi")
public class HoanThi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reason_hoan_thi_id")
    private Long id;

    @Column(nullable = false)
    private String hoanThiName;

    private boolean action = true;
};
