package Controller;

import Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserDTO;
import entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.wallet.WalletApplication;  // Adicione este import

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WalletApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
    private static final Long ID = 1L;
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
        BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());

        mvc.perform(MockMvcRequestBuilders.post(URL)
                        .content(getJsonPayload(ID, EMAIL, NAME, PASSWORD))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.email").value(EMAIL))
                .andExpect(jsonPath("$.data.name").value(NAME))
                .andExpect(jsonPath("$.data.password").value(PASSWORD));
    }

    @Test
    public void testSaveInvalidUser() throws Exception {  // Corrigido a sintaxe aqui
        mvc.perform(MockMvcRequestBuilders.post(URL)
                        .content(getJsonPayload(ID, "email", NAME, PASSWORD))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("Email invalido"));  // Adicionei uma mensagem de erro esperada
    }

    public User getMockUser() {
        User u = new User();
        u.setId(ID);
        u.setEmail(EMAIL);
        u.setName(NAME);
        u.setPassword(PASSWORD);
        return u;
    }

    public String getJsonPayload(Long id, String email, String name, String password) throws JsonProcessingException {
        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setEmail(email);
        dto.setName(name);
        dto.setPassword(password);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }
}