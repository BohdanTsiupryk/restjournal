package bts.journal.controller;

import bts.journal.dto.Table;
import bts.journal.model.Group;
import bts.journal.repo.GroupRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    @GetMapping
    public String index() {
        return "redirect:front:4200";
    }

}
