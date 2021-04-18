package bts.journal.dto;

import bts.journal.model.Group;
import bts.journal.model.UserRole;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class Table {
    private List<StudentDto> students;
    private List<LessonDto> lessons;

    public static Table toDto(Group group) {
        List<StudentDto> students1 = group.getUsers()
                .stream()
                .filter(u -> u.getRole().equals(UserRole.STUDENT))
                .map(s -> StudentDto.toDto(s))
                .collect(Collectors.toList());

        List<LessonDto> lessons = group.getLessons()
                .stream()
                .map(l -> LessonDto.toDto(l))
                .collect(Collectors.toList());

        return Table.builder()
                .lessons(lessons)
                .students(students1)
                .build();
    }
}
