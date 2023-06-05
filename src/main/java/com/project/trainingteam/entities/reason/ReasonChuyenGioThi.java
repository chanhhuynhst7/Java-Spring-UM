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
@Table(name="um_reason_chuyen_gio_thi")
public class ReasonChuyenGioThi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reason_chuyen_gio_thi_id")
    private Long id;

    @Column(unique = true)
    private String reasonChuyenGioThiName;

    private String reasonChuyenGioThiDesc;

    private boolean action  = true;
};
