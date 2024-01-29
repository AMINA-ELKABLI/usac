package ma.youcode.usac_last.payloads.request;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
