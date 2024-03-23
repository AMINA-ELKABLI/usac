package ma.youcode.usac_last.usac.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @ManyToMany(mappedBy = "children",fetch = FetchType.LAZY)
    private Set<Equip> equips = new HashSet<>();

}
