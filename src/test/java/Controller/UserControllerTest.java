package Controller;

import com.wallet.WalletApplication;
import Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserDTO;
import entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import response.Response;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WalletApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
    private static final String EMAIL = "email@teste.com";
    private static final String NAME = "User Test";
    private static final String PASSWORD = "123456";
    private static final String URL = "/user";

    @MockBean
    private UserService service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testSave() throws Exception {
        User mockUser = getMockUser();
        when(service.save(any(User.class))).thenReturn(mockUser);

        mvc.perform(MockMvcRequestBuilders.post(URL)
                        .content(getJsonPayload())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.email").value(EMAIL))
                .andExpect(jsonPath("$.data.name").value(NAME));
    }

    private User getMockUser() {
        User u = new User();
        u.setEmail(EMAIL);
        u.setName(NAME);
        u.setPassword(PASSWORD);
        return u;
    }

    private String getJsonPayload() throws JsonProcessingException {
        UserDTO dto = new UserDTO();
        dto.setEmail(EMAIL);
        dto.setName(NAME);
        dto.setPassword(PASSWORD);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }
}