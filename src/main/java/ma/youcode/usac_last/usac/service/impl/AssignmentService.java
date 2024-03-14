package ma.youcode.usac_last.usac.service.impl;

import lombok.AllArgsConstructor;
import ma.youcode.usac_last.usac.repository.EquipRepository;
import ma.youcode.usac_last.usac.repository.MatchRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssignmentService {
    private final EquipRepository equipRepository;
    private final MatchRepository matchRepository;
}
