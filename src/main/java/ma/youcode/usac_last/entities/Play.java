package ma.youcode.usac_last.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Play {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime schedule; // Horaire de l'activité
    private int maxCapacity;

    @ManyToMany
    @JoinTable(
            name = "reservation",
            joinColumns = @JoinColumn(name = "play_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id")
    )
    private Set<Child> children = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;




}
