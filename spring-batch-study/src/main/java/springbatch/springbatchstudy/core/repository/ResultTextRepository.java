package springbatch.springbatchstudy.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbatch.springbatchstudy.core.domain.ResultText;

public interface ResultTextRepository extends JpaRepository<ResultText, Long> {
}
