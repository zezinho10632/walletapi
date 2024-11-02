package Service;

import Service.Impl.UserServiceImpl;  // Adicione este import
import com.repository.UserRepository;
import com.wallet.WalletApplication;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.context.annotation.Import;  // Adicione este import

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WalletApplication.class)
@ActiveProfiles("test")
@Import(UserServiceImpl.class)  // Adicione esta anotação
public class UserServiceTest {

    @MockBean
    UserRepository repository;

    @Autowired
    UserService service;

    @BeforeEach
    public void setUp() {
        BDDMockito.given(repository.findByEmailEquals(Mockito.anyString()))
                .willReturn(Optional.of(new User()));
    }

    @Test
    public void testFindByEmail() {
        Optional<User> user = service.findByEmail("email@teste.com");
        assertTrue(user.isPresent());
    }
}