package repository;

import model.Myth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MythRepository extends JpaRepository<Myth, Long> {
    List<Myth> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String keyword, String keyword1);

    List<Myth> findByCharactersContaining(String character);

    List<Myth> findByThemesContaining(String theme);
}
