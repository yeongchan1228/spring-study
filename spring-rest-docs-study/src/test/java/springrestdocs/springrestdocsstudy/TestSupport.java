package springrestdocs.springrestdocsstudy;

import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@SpringBootTest
@AutoConfigureMockMvc
@Import(RestDocsConfiguration.class)
@ExtendWith(RestDocumentationExtension.class)
public class TestSupport {

    @Autowired protected MockMvc mockMvc;
    @Autowired protected RestDocumentationResultHandler restDocs;
    @Autowired private ResourceLoader resourceLoader;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(MockMvcResultHandlers.print()) // andDo(print()) 대체
                .alwaysDo(restDocs) // .andDo(document("{class-name}/{method-name}")); 및 JSON 알아보기 쉽게 대체
                .build();
//                .apply(springSecurity()).build(); // spring secutiry 쓰고있으면 추가함
    }

    protected String readJson(final String path) throws IOException {
        return IOUtils.toString(resourceLoader.getResource("classpath:" + path).getInputStream(), StandardCharsets.UTF_8);
    }

}
