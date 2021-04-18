package bts.journal.dto;

import bts.journal.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class StudentDto {
    private long id;
    private String name;
    private String email;
    private List<HomeworkDto> homeworks;

    public static StudentDto toDto(User student) {
        List<HomeworkDto> homeworkDtos = student.getHomeworks()
                .stream()
                .map(hw -> HomeworkDto.toDto(hw))
                .collect(Collectors.toList());

        return StudentDto.builder()
                .id(student.getId())
                .email(student.getEmail())
                .name(student.getName())
                .homeworks(homeworkDtos)
                .build();
    }
}
