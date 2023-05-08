package com.example.service_lesson.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;
    @Column(nullable = false)
    private String title;
    @ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(nullable = false)
    @ToString.Exclude
    @Schema(hidden = true)
    private Lesson lesson;
}
