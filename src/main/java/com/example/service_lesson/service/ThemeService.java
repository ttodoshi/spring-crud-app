package com.example.service_lesson.service;

import com.example.service_lesson.model.Theme;
import com.example.service_lesson.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ThemeService {

    private final ThemeRepository themeRepository;

    @Autowired
    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public List<Theme> getThemes() {
        return themeRepository.findAll();
    }

    public Theme getThemeById(Long id) {
        return themeRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void addNewThemes(List<Theme> themes) {
        themeRepository.saveAll(themes);
    }

    public void deleteTheme(Long id) {
        if (!themeRepository.existsById(id)) {
            throw new IllegalStateException("Theme with id " + id + " does not exist");
        }
        themeRepository.deleteById(id);
    }

    public void updateTheme(Long id, Theme theme) {
        Theme updatedTheme = themeRepository.findById(id).orElseThrow(() -> new IllegalStateException("Theme with id " + id + " does not exist"));
        if (theme.getTitle() != null && theme.getTitle().length() > 0)
            updatedTheme.setTitle(theme.getTitle());
        updatedTheme.setLesson(theme.getLesson());
        themeRepository.save(updatedTheme);
    }

}
