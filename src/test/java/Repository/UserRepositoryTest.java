package Repository;

import com.repository.UserRepository;
import entity.User;
import com.wallet.WalletApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = WalletApplication.class)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void testSave() {
        User u = new User();
        u.setName("Teste");
        u.setPassword("123456");
        u.setEmail("teste@teste.com");

        User response = userRepository.save(u);

        assertNotNull(response);
        assertThat(response.getEmail()).isEqualTo("teste@teste.com");
    }

    @Test
    public void testFindByEmail() {
        // Cria e persiste um novo usuário
        User u = new User();
        u.setName("Teste");
        u.setPassword("123456");
        u.setEmail("teste@teste.com");
        entityManager.persist(u);
        entityManager.flush();

        // Busca o usuário pelo email
        Optional<User> response = userRepository.findByEmailEquals("teste@teste.com");

        assertTrue(response.isPresent());
        assertEquals("teste@teste.com", response.get().getEmail());
    }

    @Test
    public void whenFindById_thenReturnUser() {
        // Criando um novo usuário para o teste
        User user = new User();
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("password");

        // Persistindo o usuário no banco de dados
        entityManager.persist(user);
        entityManager.flush();

        // Buscando o usuário pelo ID
        User found = userRepository.findById(user.getId()).orElse(null);

        // Verificando se o usuário encontrado não é nulo e possui os dados esperados
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(user.getName());
        assertThat(found.getEmail()).isEqualTo(user.getEmail());
    }
}
