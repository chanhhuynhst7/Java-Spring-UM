package com.project.trainingteam.dto.letter;


import com.project.trainingteam.entities.file.File;
import com.project.trainingteam.entities.letter.GroupLetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LetterDto {

    private Long id;

    private String username;

    private String fullname;

    private String classUser;

    private String facultyName;

    private String major;

    private String phone;

    private String reason;

    private String semesterName;

    private Date processedDate;

    private Date resultDate;

    private String note = "";

    private Integer status = 0;

    private Integer result = 0;

    private String handlePart;

    private boolean isDeleted;

    private String groupLetterName;

    private List<File> file;


}
