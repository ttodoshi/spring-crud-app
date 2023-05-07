package com.example.service_lesson.controller;

import com.example.service_lesson.dto.ThemeDto;
import com.example.service_lesson.model.Theme;
import com.example.service_lesson.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/themes/")
public class ThemeController {
    private final ThemeService themeService;

    @Autowired
    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping
    public ResponseEntity<List<ThemeDto>> getThemes() {
        return ResponseEntity.ok(themeService.getThemes());
    }

    @GetMapping(path = "{themeId}")
    public ResponseEntity<ThemeDto> getTheme(@PathVariable("themeId") Long id) {
        return ResponseEntity.ok(themeService.getThemeById(id));
    }

    @PostMapping
    public ResponseEntity<List<ThemeDto>> addNewThemes(@RequestBody List<Theme> themes) {
        return new ResponseEntity<>(themeService.addNewThemes(themes), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{themeId}")
    public ResponseEntity<String> deleteTheme(@PathVariable("themeId") Long id) {
        themeService.deleteTheme(id);
        return ResponseEntity.ok("Тема удалена");
    }

    @PutMapping(path = "{themeId}")
    public ResponseEntity<ThemeDto> updateTheme(@PathVariable("themeId") Long id,
                                                @RequestBody Theme theme) {
        return ResponseEntity.ok(themeService.updateTheme(id, theme));
    }
}
