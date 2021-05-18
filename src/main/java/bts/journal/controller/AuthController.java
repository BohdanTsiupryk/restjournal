package bts.journal.controller;

import bts.journal.config.model.JwtResp;
import bts.journal.config.model.LoginReq;
import bts.journal.config.model.RegisterReq;
import bts.journal.config.model.TokenData;
import bts.journal.config.security.JwtProvider;
import bts.journal.model.User;
import bts.journal.model.UserRole;
import bts.journal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/reg")
    public ResponseEntity<HttpStatus> reg(@RequestBody RegisterReq request) {
        if (userService.checkEmailExist(request.getEmail())) {
            return ResponseEntity.status(403).build();
        }

        String secPass = passwordEncoder.encode(request.getPassword());

        User user = User.of()
                .email(request.getEmail())
                .password(secPass)
                .name(request.getEmail())
                .role(UserRole.STUDENT)
                .build();
        userService.addUser(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResp> auth(@RequestBody LoginReq request) {
        User userEntity = userService.findByLoginAndPass(request.getEmail(), request.getPassword());
        TokenData tokenData = new TokenData(userEntity.getEmail(), userEntity.getRole());

        String token = jwtProvider.generateToken(tokenData);
        JwtResp build = JwtResp.builder()
                .token(token)
                .build();
        return ResponseEntity.ok(build);
    }

    @GetMapping("/check-role")
    public ResponseEntity<UserRole> validateRole(@RequestHeader(name = "Authorization", required = false) String token, HttpServletRequest request) {
        UserRole role = token == null ? UserRole.STUDENT : jwtProvider.getRoleFromToken(token);

        return ResponseEntity.ok(role);
    }
}
