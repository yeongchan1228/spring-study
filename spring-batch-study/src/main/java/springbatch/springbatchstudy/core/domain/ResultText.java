package springbatch.springbatchstudy.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@DynamicUpdate
@Table(name = "result_text")
@AllArgsConstructor
@NoArgsConstructor
public class ResultText {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String text;
}
