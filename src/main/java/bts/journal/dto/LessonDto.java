package bts.journal.dto;

import bts.journal.model.Lesson;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LessonDto {
    private long id;
    private String description;
    private LocalDate date;
    private LocalDate deadline;

    public static LessonDto toDto(Lesson lesson) {
        return LessonDto.builder()
                .id(lesson.getId())
                .date(lesson.getCreationDate())
                .description(lesson.getDescription())
                .deadline(lesson.getDeadLine())
                .build();
    }
}
