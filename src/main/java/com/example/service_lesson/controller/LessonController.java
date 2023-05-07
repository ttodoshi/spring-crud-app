package com.example.service_lesson.controller;

import com.example.service_lesson.dto.LessonDto;
import com.example.service_lesson.model.Lesson;
import com.example.service_lesson.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get all lessons", tags = "lesson")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lessons list",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<List<LessonDto>> getLessons() {
        return ResponseEntity.ok(lessonService.getLessons());
    }

    @GetMapping(path = "{lessonId}")
    @Operation(summary = "Get lesson by id", tags = "lesson")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Requested lesson",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<LessonDto> getLesson(@PathVariable("lessonId") Long id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    @PostMapping
    @Operation(summary = "Create new lesson", tags = "lesson")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Theme created",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<List<LessonDto>> addNewLessons(@RequestBody List<Lesson> lessons) {
        return new ResponseEntity<>(lessonService.addNewLessons(lessons), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{lessonId}")
    @Operation(summary = "Delete lesson by id", tags = "lesson")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lesson has been deleted",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<String> deleteLesson(@PathVariable("lessonId") Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.ok("Занятие удалено");
    }

    @PutMapping(path = "{lessonId}")
    @Operation(summary = "Update lesson by id", tags = "lesson")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lesson has been updated",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<LessonDto> updateLesson(@PathVariable("lessonId") Long id,
                                                  @RequestBody Lesson lesson) {
        return ResponseEntity.ok(lessonService.updateLesson(id, lesson));
    }
}
