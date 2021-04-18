package bts.journal.repo;

import bts.journal.model.Homework;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HomeworkRepo  extends CrudRepository<Homework, Long> {
}
