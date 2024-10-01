package model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class Myth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ElementCollection
    @CollectionTable(name = "myth_characters", joinColumns = @JoinColumn(name = "myth_id"))  // Définir une table pour les personnages
    @Column(name = "character")
    private List<String> characters;

    @ElementCollection
    @CollectionTable(name = "myth_themes", joinColumns = @JoinColumn(name = "myth_id"))  // Définir une table pour les thèmes
    @Column(name = "theme")
    private List<String> themes;

    @ManyToMany(mappedBy = "favoriteMyths")
    private Set<User> favoritedByUsers;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)  // L'auteur est un utilisateur, relation ManyToOne
    private User author;
}
