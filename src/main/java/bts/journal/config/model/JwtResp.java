package bts.journal.config.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtResp {
    private String token;
}
