package bts.journal.repo;

import bts.journal.model.Group;
import bts.journal.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepo extends CrudRepository<Group, Long> {

    List<Group> findAll();

}
