package springbatch.springbatchstudy.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerSalaryDto {

    private String Id;
    private String lastName;
    private String firstName;
    private String position;
    private int birthYear;
    private int debutYear;
    private int salary;

    public static PlayerSalaryDto of(PlayerDto playerDto, int salary) {
        return new PlayerSalaryDto(playerDto.getId(), playerDto.getLastName(), playerDto.getFirstName(), playerDto.getPosition(),
                playerDto.getBirthYear(), playerDto.getDebutYear(), salary);
    }

}
