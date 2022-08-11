package springbatch.springbatchstudy.job.parallel;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;
import springbatch.springbatchstudy.dto.AmountDto;

public class AmountFieldSetMapper implements FieldSetMapper<AmountDto> {
    @Override
    public AmountDto mapFieldSet(FieldSet fieldSet) throws BindException {
        AmountDto amountDto = new AmountDto();

        amountDto.setIndex(fieldSet.readInt(0));
        amountDto.setName(fieldSet.readString(1));
        amountDto.setAmount(fieldSet.readInt(2));

        return amountDto;
    }
}
