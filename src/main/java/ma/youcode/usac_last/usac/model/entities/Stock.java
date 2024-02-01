package ma.youcode.usac_last.usac.model.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.youcode.usac_last.usac.model.enums.MaterialCondition;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String materialName;
    private String description;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private MaterialCondition condition;
}
