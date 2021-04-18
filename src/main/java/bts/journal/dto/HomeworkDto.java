package bts.journal.dto;

import bts.journal.model.Homework;
import bts.journal.model.HomeworkStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HomeworkDto {
    private long id;
    private long lessonId;
    private String description;
    private HomeworkStatus status;

    public static HomeworkDto toDto(Homework homework) {
        return HomeworkDto.builder()
                .id(homework.getId())
                .description(homework.getDescription())
                .lessonId(homework.getLesson().getId())
                .status(homework.getStatus())
                .build();
    }
}
