package springbatch.springbatchstudy.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springbatch.springbatchstudy.core.domain.PlainText;

public interface PlainTextRepository extends JpaRepository<PlainText, Long> {
    Page<PlainText> findBy(Pageable pageable);
}
