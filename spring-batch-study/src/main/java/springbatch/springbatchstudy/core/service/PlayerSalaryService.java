package springbatch.springbatchstudy.core.service;

import org.springframework.stereotype.Service;
import springbatch.springbatchstudy.dto.PlayerDto;
import springbatch.springbatchstudy.dto.PlayerSalaryDto;

import java.time.Year;

@Service
public class PlayerSalaryService {

    public PlayerSalaryDto calcSalary(PlayerDto player) {
        int salary = (Year.now().getValue() - player.getBirthYear()) * 100000;

        return PlayerSalaryDto.of(player, salary);
    }
}
