package com.example.service_lesson.controller;

import com.example.service_lesson.model.Lesson;
import com.example.service_lesson.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons/")
public class LessonController {
    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public List<Lesson> getLessons() {
        return lessonService.getLessons();
    }

    @GetMapping(path = "{lessonId}")
    public Lesson getLesson(@PathVariable("lessonId") Long id) {
        return lessonService.getLessonById(id);
    }

    @PostMapping
    public void addNewLessons(@RequestBody List<Lesson> lessons) {
        lessonService.addNewLessons(lessons);
    }

    @DeleteMapping(path = "{lessonId}")
    public void deleteLesson(@PathVariable("lessonId") Long id) {
        lessonService.deleteLesson(id);
    }

    @PutMapping(path = "{lessonId}")
    public void updateLesson(@PathVariable("lessonId") Long id, @RequestBody Lesson lesson) {
        lessonService.updateLesson(id, lesson);
    }

}
