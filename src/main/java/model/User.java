package model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "app_user")  // Nom de la table explicite
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))  // Définir une table intermédiaire
    @Column(name = "role")  // Nom de la colonne qui stockera chaque rôle
    private Set<String> roles;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_myths",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "myth_id")
    )
    private Set<Myth> favoriteMyths;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
}
