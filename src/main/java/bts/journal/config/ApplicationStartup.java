package bts.journal.config;

import bts.journal.model.User;
import bts.journal.model.UserRole;
import bts.journal.repo.UserRepo;
import bts.journal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepo userRepo;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        long count = userRepo.findAll().size();

        if (count == 0) {
            addAdmin();
        }
    }

    private void addAdmin() {
        String secPass = passwordEncoder.encode("1");

        User user = User.of()
                .email("email")
                .password(secPass)
                .name("email")
                .role(UserRole.STUDENT)
                .build();
        userService.addUser(user);
    }

}
