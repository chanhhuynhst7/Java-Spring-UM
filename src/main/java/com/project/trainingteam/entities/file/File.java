package com.project.trainingteam.entities.file;


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
@Table(name="um_file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long letterId;

    @Column(nullable = false)
    private String groupLetterName;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;
}
