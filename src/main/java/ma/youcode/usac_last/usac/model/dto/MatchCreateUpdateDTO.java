package ma.youcode.usac_last.usac.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
@Getter
public final class MatchCreateUpdateDTO {
        private final @NotBlank(message = "Le nom ne peut pas être vide") @Size(max = 255, message = "La longueur du nom ne peut pas dépasser 255 caractères") String name;
        private final @NotNull(message = "La date du match ne peut pas être nulle") LocalDateTime matchDate;
        private final @NotBlank(message = "L'emplacement ne peut pas être vide") @Size(max = 255, message = "La longueur de l'emplacement ne peut pas dépasser 255 caractères") String location;
        private final @NotBlank(message = "Le type ne peut pas être vide") @Size(max = 255, message = "La longueur du type ne peut pas dépasser 255 caractères") String type;
        private final Long equipOne;
        private final Long equipTwo;

        public MatchCreateUpdateDTO(
                @NotBlank(message = "Le nom ne peut pas être vide")
                @Size(max = 255, message = "La longueur du nom ne peut pas dépasser 255 caractères")
                String name,

                @NotNull(message = "La date du match ne peut pas être nulle")
                @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
                LocalDateTime matchDate,

                @NotBlank(message = "L'emplacement ne peut pas être vide")
                @Size(max = 255, message = "La longueur de l'emplacement ne peut pas dépasser 255 caractères")
                String location,

                @NotBlank(message = "Le type ne peut pas être vide")
                @Size(max = 255, message = "La longueur du type ne peut pas dépasser 255 caractères")
                String type,

                Long equipOne,
                Long equipTwo
        ) {
                this.name = name;
                this.matchDate = matchDate;
                this.location = location;
                this.type = type;
                this.equipOne = equipOne;
                this.equipTwo = equipTwo;
        }

        public @NotBlank(message = "Le nom ne peut pas être vide") @Size(max = 255, message = "La longueur du nom ne peut pas dépasser 255 caractères") String name() {
                return name;
        }

        public @NotNull(message = "La date du match ne peut pas être nulle") LocalDateTime matchDate() {
                return matchDate;
        }

        public @NotBlank(message = "L'emplacement ne peut pas être vide") @Size(max = 255, message = "La longueur de l'emplacement ne peut pas dépasser 255 caractères") String location() {
                return location;
        }

        public @NotBlank(message = "Le type ne peut pas être vide") @Size(max = 255, message = "La longueur du type ne peut pas dépasser 255 caractères") String type() {
                return type;
        }

        public Long equipOne() {
                return equipOne;
        }

        public Long equipTwo() {
                return equipTwo;
        }

        @Override
        public boolean equals(Object obj) {
                if (obj == this) return true;
                if (obj == null || obj.getClass() != this.getClass()) return false;
                var that = (MatchCreateUpdateDTO) obj;
                return Objects.equals(this.name, that.name) &&
                        Objects.equals(this.matchDate, that.matchDate) &&
                        Objects.equals(this.location, that.location) &&
                        Objects.equals(this.type, that.type) &&
                        Objects.equals(this.equipOne, that.equipOne) &&
                        Objects.equals(this.equipTwo, that.equipTwo);
        }

        @Override
        public int hashCode() {
                return Objects.hash(name, matchDate, location, type, equipOne, equipTwo);
        }

        @Override
        public String toString() {
                return "MatchCreateUpdateDTO[" +
                        "name=" + name + ", " +
                        "matchDate=" + matchDate + ", " +
                        "location=" + location + ", " +
                        "type=" + type + ", " +
                        "equipOne=" + equipOne + ", " +
                        "equipTwo=" + equipTwo + ']';
        }

}
