package com.project.trainingteam.dto.letter;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LetterDto {

    private Long id ;

    private String groupLetter;

    private String username;

    private String fullname;

    private String classUser;

    private String facultyName;

    private String major;

    private String phone;

    private String semesterName;

    private String reason;

    private Date processedDate;

    private Date resultDate;

    private String note;

    private Integer status;

    private Integer result;

    private String handlePart;
}
