package springbatch.springbatchstudy.core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import springbatch.springbatchstudy.dto.PlayerDto;
import springbatch.springbatchstudy.dto.PlayerSalaryDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PlayerSalaryServiceTest {

    @Mock private PlayerDto playerDto;
    @InjectMocks private PlayerSalaryService playerSalaryService;

    @Test
    public void calcSalary() throws Exception {
        // given 조건 설정
//        Year mockYear = Mockito.mock(Year.class);
//        when(mockYear.getValue()).thenReturn(2022);
//        mockStatic(Year.class).when(Year::now).thenReturn(mockYear);

        given(playerDto.getBirthYear()).willReturn(1980);

        // when
        PlayerSalaryDto playerSalaryDto = playerSalaryService.calcSalary(playerDto);

        // then
        assertThat(playerSalaryDto.getSalary()).isEqualTo(4200000);

    }
}