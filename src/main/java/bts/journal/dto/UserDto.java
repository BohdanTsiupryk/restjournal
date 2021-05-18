package bts.journal.dto;

import bts.journal.model.Group;
import bts.journal.model.User;
import bts.journal.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long id;

    private String name;

    private String email;

    private UserRole role;

    private List<HomeworkDto> homeworks;

    private List<Long> groups;

    public static UserDto toDto(User user) {
        List<HomeworkDto> homeworks = user.getHomeworks()
                .stream()
                .map(HomeworkDto::toDto)
                .collect(Collectors.toList());

        List<Long> groupIds = user.getGroups()
                .stream()
                .map(Group::getId)
                .collect(Collectors.toList());

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .homeworks(homeworks)
                .groups(groupIds)
                .build();
    }

}
