package ma.youcode.usac_last.usac.model.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.youcode.usac_last.usac.model.enums.Gender;
import ma.youcode.usac_last.usac.model.enums.Status;


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
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;
    private String guardianName;
    private String guardianContact;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status= Status.PENDING;
    @ManyToMany
    @JoinTable(
            name = "child_equip",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "equip_id")
    )
    private Set<Equip> equips = new HashSet<>();


}
