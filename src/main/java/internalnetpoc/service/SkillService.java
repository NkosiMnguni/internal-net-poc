package internalnetpoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internalnetpoc.dao.SkillDao;
import internalnetpoc.dto.PersonSkillDTO;


@Service
public class SkillService {
    SkillDao skillDao;
    @Autowired
    public SkillService(SkillDao skillDao) {
        this.skillDao = skillDao;
    }
    public List<PersonSkillDTO> getSkillRatings() {
        return skillDao.getSkillRatings();
    }
}
