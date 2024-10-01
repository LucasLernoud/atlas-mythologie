package service.impl;

import model.Myth;
import org.springframework.beans.factory.annotation.Autowired;
import repository.MythRepository;
import service.MythService;

import java.util.List;

public class MythServiceImpl implements MythService {
    @Autowired
    private MythRepository mythRepository;

    public List<Myth> findAll() {
        return mythRepository.findAll();
    }

    public List<Myth> searchMyths(String keyword) {
        return mythRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
    }

    public List<Myth> filterMythsByCharacter(String character) {
        return mythRepository.findByCharactersContaining(character);
    }

    public List<Myth> filterMythsByTheme(String theme) {
        return mythRepository.findByThemesContaining(theme);
    }

    public Myth createMyth(Myth myth) {
        return mythRepository.save(myth);
    }

    public void deleteMyth(Long id) {
        mythRepository.deleteById(id);
    }
}
