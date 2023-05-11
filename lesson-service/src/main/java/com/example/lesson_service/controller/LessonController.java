package com.example.lesson_service.controller;

import com.example.lesson_service.dto.LessonDto;
import com.example.lesson_service.model.Lesson;
import com.example.lesson_service.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons/")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

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
    @Secured("ROLE_ADMIN")
    @Operation(summary = "Create new lesson", tags = "lesson")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
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
    @Secured("ROLE_ADMIN")
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
    @Secured("ROLE_ADMIN")
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
