package bts.journal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue
    private long id;

    private String description;

    private LocalDate creationDate;

    private LocalDate deadLine;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    private List<Homework> homeworks;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public Lesson(String description, LocalDate creationDate, LocalDate deadLine, List<Homework> homeworks, Group group) {
        this.description = description;
        this.creationDate = creationDate;
        this.deadLine = deadLine;
        this.homeworks = homeworks;
        this.group = group;
    }
}
