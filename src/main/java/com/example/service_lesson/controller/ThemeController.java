package com.example.service_lesson.controller;

import com.example.service_lesson.model.Theme;
import com.example.service_lesson.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Theme> getThemes() {
        return themeService.getThemes();
    }

    @GetMapping(path = "{themeId}")
    public Theme getTheme (@PathVariable("themeId") Long id) {
        return themeService.getThemeById(id);
    }

    @PostMapping
    public void addNewThemes(@RequestBody List<Theme> themes) {
        themeService.addNewThemes(themes);
    }

    @DeleteMapping(path = "{themeId}")
    public void deleteTheme(@PathVariable("themeId") Long id) {
        themeService.deleteTheme(id);
    }

    @PutMapping(path = "{themeId}")
    public void updateTheme(@PathVariable("themeId") Long id, @RequestBody Theme theme) {
        themeService.updateTheme(id, theme);
    }
}
