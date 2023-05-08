package com.example.service_lesson.service;

import com.example.service_lesson.dto.ThemeDto;
import com.example.service_lesson.model.Theme;
import com.example.service_lesson.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final ModelMapper mapper;

    public List<ThemeDto> getThemes() {
        return themeRepository.findAll()
                .stream()
                .map(t -> mapper.map(t, ThemeDto.class))
                .collect(Collectors.toList());
    }

    public ThemeDto getThemeById(Long id) {
        return mapper.map(themeRepository.findById(id).orElseThrow(), ThemeDto.class);
    }

    // Аннотация для проведении транзакции в БД после выполнения метода
    @Transactional
    public List<ThemeDto> addNewThemes(List<Theme> themes) {
        return themeRepository.saveAll(themes)
                .stream()
                .map(t -> mapper.map(t, ThemeDto.class))
                .collect(Collectors.toList());
    }

    public void deleteTheme(Long id) {
        if (!themeRepository.existsById(id)) {
            throw new IllegalStateException("Theme with id " + id + " does not exist");
        }
        themeRepository.deleteById(id);
    }

    public ThemeDto updateTheme(Long id, Theme theme) {
        Theme updatedTheme = themeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Theme with id " + id + " does not exist"));
        if (theme.getTitle() != null && theme.getTitle().length() > 0)
            updatedTheme.setTitle(theme.getTitle());
        updatedTheme.setLesson(theme.getLesson());
        return mapper.map(themeRepository.save(updatedTheme), ThemeDto.class);
    }
}
