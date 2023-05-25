package com.project.trainingteam.entities.file;


import com.project.trainingteam.entities.letter.Letter;
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
    @Column(name = "file_id")
    private Long id;

    private String fileName;

    private Long letterId;

    private String fileType;

    private String downloadUrl;

    @Lob
    private byte[] data;

}
