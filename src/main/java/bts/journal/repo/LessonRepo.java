package bts.journal.repo;

import bts.journal.model.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepo  extends CrudRepository<Lesson, Long> {
}
