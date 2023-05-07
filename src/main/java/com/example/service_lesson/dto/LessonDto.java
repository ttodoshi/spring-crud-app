package com.example.service_lesson.dto;

import com.example.service_lesson.model.Theme;
import lombok.Data;

import java.util.List;

@Data
public class LessonDto {
    private String title;
    public List<ThemeDto> themes;
    private Long courseId;
}
