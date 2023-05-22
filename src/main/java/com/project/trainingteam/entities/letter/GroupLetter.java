package com.project.trainingteam.entities.letter;

import com.project.trainingteam.entities.base.Auditable;
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
@Table(name="um_group_letter")
public class GroupLetter extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_letter_id")
    private Long id;

    @Column(nullable = false)
    private String groupLetterName;

    @Column(nullable = false)
    private String groupLetterCode;

    private String descGroupLetter;

    private boolean action = true;

    //Database Relationship


}
