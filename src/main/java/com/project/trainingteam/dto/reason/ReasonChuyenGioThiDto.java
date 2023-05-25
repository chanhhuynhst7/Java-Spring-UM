package com.project.trainingteam.dto.reason;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReasonChuyenGioThiDto {

    private Long id;

    private String reasonChuyenGioThiName;

    private String descReasonChuyenGioThi;

    private boolean action;
}
