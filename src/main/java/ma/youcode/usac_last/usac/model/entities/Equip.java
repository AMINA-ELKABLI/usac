package ma.youcode.usac_last.usac.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "child_equip",
            joinColumns = @JoinColumn(name = "equip_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id")
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Child> children = new HashSet<>();
    @ManyToMany(mappedBy = "equips",fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Match> matches = new HashSet<>();
}
