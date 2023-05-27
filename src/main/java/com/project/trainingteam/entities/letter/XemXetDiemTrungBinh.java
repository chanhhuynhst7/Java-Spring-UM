package com.project.trainingteam.entities.letter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="um_xem_xet_diem_trung_binh")
public class XemXetDiemTrungBinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "xem_xet_diem_trung_binh_id")
    private Long id ;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String classUser;

    @Column(nullable = false)
    private String facultyName;

    @Column(nullable = false)
    private String major;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String semesterName;

    @Column(nullable = false)
    private String pointType;

    @Column(nullable = false)
    private String reason;

    @Column(columnDefinition = "date default null")
    private Date processedDate;

    @Column(columnDefinition = "date default null")
    private Date resultDate;

    private String note = "";

    private Integer status = 0;

    private Integer result = 0;

    private String handlePart ="Thầy X - Khoa Công Nghệ Thông Tin";

    @Column(columnDefinition = "boolean default null")
    private boolean isDeleted;

    @Column(nullable = false)
    private String groupLetterName;
}
