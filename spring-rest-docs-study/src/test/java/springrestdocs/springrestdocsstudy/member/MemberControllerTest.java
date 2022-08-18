package springrestdocs.springrestdocsstudy.member;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import springrestdocs.springrestdocsstudy.TestSupport;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        .andExpect(status().isOk());
    }

    @Test
    public void member_get_test() throws Exception {
        mockMvc.perform(
                get("/api/members/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
    }

    @Test
    public void member_create_test() throws Exception {
        mockMvc.perform(
            post("/api/members")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(readJson("/json/member/member-create.json"))
        )
        .andExpect(status().isOk());
    }

    @Test
    public void member_modify_test() throws Exception {
        mockMvc.perform(
            post("/api/members/{id}", 1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(readJson("json/member/member-modify.json"))
        )
        .andExpect(status().isOk());
    }

}