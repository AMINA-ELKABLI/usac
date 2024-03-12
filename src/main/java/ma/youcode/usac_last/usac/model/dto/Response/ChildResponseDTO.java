package ma.youcode.usac_last.usac.model.dto.Response;

import ma.youcode.usac_last.usac.model.enums.Gender;
import ma.youcode.usac_last.usac.model.enums.Status;


import java.time.LocalDate;

public class ChildResponseDTO {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String address;
    private String guardianName;
    private String guardianContact;
    private Status status;


    public ChildResponseDTO() {

    }

    public ChildResponseDTO(Long id, String name, LocalDate dateOfBirth, Gender gender, String address, String guardianName, String guardianContact) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.guardianName = guardianName;
        this.guardianContact = guardianContact;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianContact() {
        return guardianContact;
    }

    public void setGuardianContact(String guardianContact) {
        this.guardianContact = guardianContact;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
