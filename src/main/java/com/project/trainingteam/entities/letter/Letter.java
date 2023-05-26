package com.project.trainingteam.entities.letter;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.trainingteam.dto.file.FileDto;
import com.project.trainingteam.entities.base.Auditable;
import com.project.trainingteam.entities.file.File;
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
@Entity
@Table(name="um_letter")
public class Letter extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_id")
    private Long id ;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    private String classUser;

    @Column(nullable = false)
    private String facultyName;

    @Column(nullable = false)
    private String major;

    @Column(nullable = false)
    private String phone;

    private String reason;

    private String semesterName;

    @Column(columnDefinition = "date default null")
    private Date processedDate;

    @Column(columnDefinition = "date default null")
    private Date resultDate;

    private String note = "";

    private Integer status = 0;

    private Integer result = 0;

    private String handlePart ="Thầy Nguyễn Văn A Khoa A";

    @Column(columnDefinition = "boolean default null")
    private boolean isDeleted;


    @Column(nullable = false)
    private String groupLetterName;


}
