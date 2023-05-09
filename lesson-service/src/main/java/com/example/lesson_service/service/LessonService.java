package com.example.lesson_service.service;

import com.example.lesson_service.dto.LessonDto;
import com.example.lesson_service.model.Lesson;
import com.example.lesson_service.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModelMapper mapper;

    public List<LessonDto> getLessons() {
        return lessonRepository.findAll()
                .stream()
                .map(l -> mapper.map(l, LessonDto.class))
                .collect(Collectors.toList());
    }

    public LessonDto getLessonById(Long id) {
        return mapper.map(lessonRepository.findById(id).orElseThrow(), LessonDto.class);
    }

    // Аннотация для проведении транзакции в БД после выполнения метода
    @Transactional
    public List<LessonDto> addNewLessons(List<Lesson> lessons) {
        return lessonRepository.saveAll(lessons)
                .stream()
                .map(l -> mapper.map(l, LessonDto.class))
                .collect(Collectors.toList());
    }

    public void deleteLesson(Long id) {
        if (!lessonRepository.existsById(id)) {
            throw new IllegalStateException("Lesson with id " + id + " does not exist");
        }
        lessonRepository.deleteById(id);
    }

    public LessonDto updateLesson(Long id, Lesson lesson) {
        Lesson updatedLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Lesson with id " + id + " does not exist"));
        if (lesson.getTitle() != null && lesson.getTitle().length() > 0)
            updatedLesson.setTitle(lesson.getTitle());
        updatedLesson.setCourseId(lesson.getCourseId());
        return mapper.map(lessonRepository.save(updatedLesson), LessonDto.class);
    }
}
