package ma.youcode.usac_last.usac.model.dto;

import ma.youcode.usac_last.usac.model.enums.Status;

public class StatusUpdateDTO {
    private Status status;


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
