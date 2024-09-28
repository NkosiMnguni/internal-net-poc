package internalnetpoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import internalnetpoc.dto.PersonSkillDTO;
import internalnetpoc.service.SkillService;


@RestController
public class SkillController {
    private final SkillService skillService;
    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }
    @GetMapping("/app/v1/skill-ratings")
    public List<PersonSkillDTO> skillRatings() {
        return skillService.getSkillRatings();
    }
}
