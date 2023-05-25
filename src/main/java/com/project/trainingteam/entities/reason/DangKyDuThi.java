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
@Table(name="um_reason_dang_ky_du_thi")
public class DangKyDuThi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reason_dang_ky_du_thi_id")
    private Long id;

    @Column(nullable = false)
    private String dangKyDuThiName;

    private boolean action = true ;

};
