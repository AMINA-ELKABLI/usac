package ma.youcode.usac_last.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String gender; // Peut être "Male", "Female", "Other"
    private String address;
    private String guardianName; // Nom du tuteur
    private String guardianContact; // Contact du tuteur

    @ManyToMany(mappedBy = "children")
    private Set<Play> plays = new HashSet<>();
    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MatchChild> matchChildren = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "equip_id")
    private Equip equip;

}
