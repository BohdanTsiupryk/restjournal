package bts.journal.controller;

import bts.journal.dto.Table;
import bts.journal.model.Group;
import bts.journal.model.Homework;
import bts.journal.model.HomeworkStatus;
import bts.journal.model.Lesson;
import bts.journal.model.User;
import bts.journal.model.UserRole;
import bts.journal.repo.GroupRepo;
import bts.journal.repo.HomeworkRepo;
import bts.journal.repo.LessonRepo;
import bts.journal.repo.UserRepo;
import bts.journal.util.UserValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/table")
@RequiredArgsConstructor
public class TableController {

    private final GroupRepo groupRepo;
    private final UserRepo userRepo;
    private final HomeworkRepo homeworkRepo;
    private final LessonRepo lessonRepo;

    @GetMapping
    public ResponseEntity<Table> getTable(@RequestParam long groupId) {
        Group group = groupRepo.findById(groupId).get();

        Table table = Table.toDto(group);
        return ResponseEntity.ok(table);
    }

    @GetMapping("/mock-user")
    public ResponseEntity<HttpStatus> mockUser() {
        User student = new User("student", "student-email", "pass", UserRole.STUDENT);
        User teacher = new User("teacher", "teacher-email", "pass", UserRole.TEACHER);

        userRepo.save(student);
        userRepo.save(teacher);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mock-group")
    public ResponseEntity<HttpStatus> mock() {
             List<User> all = userRepo.findAll();

        Group group = new Group();
        group.setUsers(all);

        groupRepo.save(group);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/mock-lh")
    public ResponseEntity<HttpStatus> mockLesson() {
        Group group = groupRepo.findAll()
                .stream()
                .findFirst()
                .get();

        Lesson lesson = new Lesson();
        lesson.setGroup(group);
        lesson.setCreationDate(LocalDate.now());
        lesson.setDeadLine(LocalDate.now());
        lesson.setDescription("som description");

        Lesson savedLesson = lessonRepo.save(lesson);

        userRepo.findAll()
                .stream()
                .filter(u -> u.getRole().equals(UserRole.STUDENT))
                .map(s -> new Homework(s, savedLesson, "hw desc", HomeworkStatus.IN_PROGRESS))
                .forEach(h -> homeworkRepo.save(h));

        return ResponseEntity.ok().build();
    }
}
