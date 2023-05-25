package com.project.trainingteam.dto.reason;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReasonDangKyDuThiDto {

    private Long id;

    private String reasonDangKyDuThiName;

    private String descReasonDangKyDuThi;

    private boolean action;
}
