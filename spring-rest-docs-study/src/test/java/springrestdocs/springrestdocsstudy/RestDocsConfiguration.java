package springrestdocs.springrestdocsstudy;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;

import static org.springframework.restdocs.snippet.Attributes.*;

@TestConfiguration
public class RestDocsConfiguration {

    /**
     * document 패키지 지정
     * JSON 응답, 요청 형식을 알아보기 쉽게 만들기 위한 설정
     */
    @Bean
    public RestDocumentationResultHandler restDocs() {
        return MockMvcRestDocumentation.document(
                "{class-name}/{method-name}",
                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
        );
    }

    public static final Attribute field(
        final String key,
        final String value
    ) {
        return new Attribute(key, value);
    }
}
