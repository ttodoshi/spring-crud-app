package com.example.service_lesson.service;

import com.example.service_lesson.model.Lesson;
import com.example.service_lesson.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void addNewLessons(List<Lesson> lessons) {
        lessonRepository.saveAll(lessons);
    }

    public void deleteLesson(Long id) {
        if (!lessonRepository.existsById(id)) {
            throw new IllegalStateException("lesson with id " + id + " does not exist");
        }
        lessonRepository.deleteById(id);
    }

    public void updateLesson(Long id, Lesson lesson) {
        Lesson updatedLesson = lessonRepository.findById(id).orElseThrow(() -> new IllegalStateException("lesson with id " + id + " does not exist"));
        if (lesson.getTitle() != null && lesson.getTitle().length() > 0)
            updatedLesson.setTitle(lesson.getTitle());
        updatedLesson.setCourseId(lesson.getCourseId());
        lessonRepository.save(updatedLesson);
    }
}
