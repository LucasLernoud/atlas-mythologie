package com.lucas.atlas_mythologie.service;

import com.lucas.atlas_mythologie.model.Myth;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MythService {

    public List<Myth> findAll();

    public List<Myth> searchMyths(String keyword);

    public List<Myth> filterMythsByCharacter(String character);

    public List<Myth> filterMythsByTheme(String theme);

    public Myth createMyth(Myth myth);

    public void deleteMyth(Long id);
}
