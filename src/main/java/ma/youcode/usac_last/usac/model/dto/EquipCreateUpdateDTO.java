package ma.youcode.usac_last.usac.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class EquipCreateUpdateDTO {
    @NotEmpty(message = "Le nom ne peut pas être vide")
    @Size(min = 3, max = 100, message = "Le nom doit contenir entre 3 et 100 caractères")
    private String name;

    @NotEmpty(message = "La description ne peut pas être vide")
    @Size(min = 10, max = 500, message = "La description doit contenir entre 10 et 500 caractères")
    private String description;

    private List<Long> childIds;


    public EquipCreateUpdateDTO(String name, String description, List<Long> childIds) {
        this.name = name;
        this.description = description;
        this.childIds = childIds;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Long> getChildIds() {
        return childIds;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setChildIds(List<Long> childIds) {
        this.childIds = childIds;
    }
}
