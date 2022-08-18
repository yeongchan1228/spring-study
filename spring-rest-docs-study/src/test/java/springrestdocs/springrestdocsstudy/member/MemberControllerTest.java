package springrestdocs.springrestdocsstudy.member;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import springrestdocs.springrestdocsstudy.TestSupport;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends TestSupport {

    @Test
        public void member_page_test() throws Exception {
            mockMvc.perform(
                get("/api/members")
                        .param("size", "10")
                        .param("page", "0")
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andDo(restDocs.document(
                requestParameters(
                        parameterWithName("size").optional().description("size"),
                        parameterWithName("page").optional().description("page")
                )
        ));
    }

    @Test
    public void member_get_test() throws Exception {
        mockMvc.perform(
                get("/api/members/{memberId}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andDo(restDocs.document(
                pathParameters(
                        parameterWithName("memberId").description("member ID")
                ),
                responseFields(
                        fieldWithPath("id").description("Member ID"),
                        fieldWithPath("name").description("name"),
                        fieldWithPath("email").description("email")
                )
        ));
    }

    @Test
    public void member_create_test() throws Exception {
        mockMvc.perform(
            post("/api/members")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(readJson("/json/member/member-create.json"))
        )
        .andExpect(status().isOk())
        .andDo(restDocs.document(
                requestFields(
                        fieldWithPath("name").description("name"),
                        fieldWithPath("email").description("email")
                )
        ));
    }

    @Test
    public void member_modify_test() throws Exception {
        mockMvc.perform(
            post("/api/members/{memberId}", 1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(readJson("json/member/member-modify.json"))
        )
        .andExpect(status().isOk())
        .andDo(restDocs.document(
                requestFields(
                        fieldWithPath("name").description("name")
                ),
                pathParameters(
                        parameterWithName("memberId").description("Member ID")
                )
        ));
    }

}