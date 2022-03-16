package com.study.springdatajpa.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "material"
)
@ToString(
        exclude = "course"
)
public class Material {
    @Id
    @SequenceGenerator(
            name = "material_sequence",
            sequenceName = "material_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "material_sequence"
    )
    public Long id;

    @Column
    private String url;

    @OneToOne(
            cascade = ALL,
            fetch = LAZY
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "id"
    )
    private Course course;
}
