package com.example.theme_service.controller;

import com.example.theme_service.dto.ThemeDto;
import com.example.theme_service.model.Theme;
import com.example.theme_service.service.ThemeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/themes/")
@RequiredArgsConstructor
public class ThemeController {
    private final ThemeService themeService;

    @GetMapping
    @Operation(summary = "Get all themes", tags = "theme")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Themes list",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<List<ThemeDto>> getThemes() {
        return ResponseEntity.ok(themeService.getThemes());
    }

    @GetMapping(path = "{themeId}")
    @Operation(summary = "Get theme by id", tags = "theme")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Requested theme",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<ThemeDto> getTheme(@PathVariable("themeId") Long id) {
        return ResponseEntity.ok(themeService.getThemeById(id));
    }

    @PostMapping
    @Operation(summary = "Create new theme", tags = "theme")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Theme created",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<List<ThemeDto>> addNewThemes(@RequestBody List<Theme> themes) {
        return new ResponseEntity<>(themeService.addNewThemes(themes), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{themeId}")
    @Operation(summary = "Delete theme by id", tags = "theme")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Theme has been deleted",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<String> deleteTheme(@PathVariable("themeId") Long id) {
        themeService.deleteTheme(id);
        return ResponseEntity.ok("Тема удалена");
    }

    @PutMapping(path = "{themeId}")
    @Operation(summary = "Update theme by id", tags = "theme")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Theme has been updated",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<ThemeDto> updateTheme(@PathVariable("themeId") Long id,
                                                @RequestBody Theme theme) {
        return ResponseEntity.ok(themeService.updateTheme(id, theme));
    }
}
