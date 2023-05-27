package com.project.trainingteam.dto.letter;


import com.project.trainingteam.entities.file.LetterFile;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class XemXetDiemTrungBinhDto {


    private Long id ;

    private String username;

    private String fullName;

    private String classUser;

    private String facultyName;

    private String major;

    private String phone;

    private String semesterName;

    private String pointType;

    private String reason;

    private Date processedDate;

    private Date resultDate;

    private String note = "";

    private Integer status = 0;

    private Integer result = 0;

    private String handlePart ="Thầy X - Khoa Công Nghệ Thông Tin";

    private boolean isDeleted;

    private String groupLetterName;

    private List<LetterFile> letterFileList ;
}
