package com.example.service_lesson.controller;

import com.example.service_lesson.dto.LessonDto;
import com.example.service_lesson.model.Lesson;
import com.example.service_lesson.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<LessonDto>> getLessons() {
        return ResponseEntity.ok(lessonService.getLessons());
    }

    @GetMapping(path = "{lessonId}")
    public ResponseEntity<LessonDto> getLesson(@PathVariable("lessonId") Long id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    @PostMapping
    public ResponseEntity<List<LessonDto>> addNewLessons(@RequestBody List<Lesson> lessons) {
        return new ResponseEntity<>(lessonService.addNewLessons(lessons), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{lessonId}")
    public ResponseEntity<String> deleteLesson(@PathVariable("lessonId") Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.ok("Занятие удалено");
    }

    @PutMapping(path = "{lessonId}")
    public ResponseEntity<LessonDto> updateLesson(@PathVariable("lessonId") Long id,
                                                  @RequestBody Lesson lesson) {
        return ResponseEntity.ok(lessonService.updateLesson(id, lesson));
    }
}
