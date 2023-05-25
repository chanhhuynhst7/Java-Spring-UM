package com.project.trainingteam.dto.reason;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReasonHoanThiDto {

    private Long id;

    private String reasonHoanThiName;

    private String descReasonHoanThi;

    private boolean action;
}
