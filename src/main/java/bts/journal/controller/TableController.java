package bts.journal.controller;

import bts.journal.dto.Table;
import bts.journal.model.Group;
import bts.journal.repo.GroupRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/table")
@RequiredArgsConstructor
public class TableController {

    private final GroupRepo groupRepo;

    @GetMapping
    public ResponseEntity<Table> getTable(@RequestParam long groupId) {
        Group group = groupRepo.findById(groupId).get();

        Table table = Table.toDto(group);
        return ResponseEntity.ok(table);
    }

}
