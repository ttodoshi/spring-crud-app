package com.example.service_lesson.dto;

import lombok.Data;

import java.util.List;

@Data
public class LessonDto {
    private String title;
    private List<ThemeDto> themes;
    private Long courseId;
}
