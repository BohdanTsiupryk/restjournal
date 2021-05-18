package bts.journal.config.model;

import bts.journal.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenData {

    private String login;
    private UserRole role;
}
