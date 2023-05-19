package com.project.trainingteam.dto.letter;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupLetterDto {

    private Long id;

    private String groupLetterName;

    private String groupLetterCode;

    private String descGroupLetter;

    private boolean action;
}
