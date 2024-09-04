import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CadastroTest {

    private Cadastro cadastro;

    @BeforeAll
    static void antesDeTodosOsTestes() {
        System.out.println("Iniciando os testes...");
    }

    @BeforeEach
    void prepararTeste() {
        cadastro = new Cadastro();
    }

    @Test
    @DisplayName("Deveria cadastrar um novo elemento")
    void testCadastrarElemento() {
        cadastro.cadastrarElemento("Cadeira");
        List<String> elementos = cadastro.getElementos();
        assertNotNull(elementos);
        assertEquals(1, elementos.size());
        assertEquals("Cadeira", elementos.get(0));
    }

    @Test
    @DisplayName("Deveria editar um elemento ja cadastrado")
    void testEditarElemento() {
        cadastro.cadastrarElemento("Cadeira");
        cadastro.editarElemento(0, "Cadeira de Balanço");
        List<String> elementos = cadastro.getElementos();
        assertEquals(1, elementos.size());
        assertEquals("Cadeira de Balanço", elementos.get(0));
    }

    @Test
    @DisplayName("Deveria lançar uma exceção ao tentar editar um elemento que não existe")
    void testEditarElementoInvalido(){
        assertThrows(IndexOutOfBoundsException.class,
                () -> cadastro.editarElemento(0, "Cadeira de Balanço"));
    }

    @Test
    @DisplayName("Deveria excluir um elemento já cadastrado")
    void testExcluirElemento() {
        cadastro.cadastrarElemento("Cadeira");
        cadastro.excluirElemento(0);
        List<String> elementos = cadastro.getElementos();
        assertTrue(elementos.isEmpty());
    }

    @Test
    @DisplayName("Deveria lançar uma exceção ao tentar excluir um elemento que não existe")
    void testExcluirElementoInvalido() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> cadastro.excluirElemento(0));
    }

    @AfterEach
    void limparDepoisDosTestes() {
        cadastro = null;
    }

    @AfterAll
    static void depoisDeTodosOsTestes() {
        System.out.println("Todos os testes finalizados!");
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC})
    @DisplayName("Esse teste só executa em Linux ou Mac")
    void testExcluirOutroElementoInvalido() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> cadastro.excluirElemento(0));
    }

    @Test
    @Order(1)
    @DisplayName("Deveria cadastrar um novo elemento")
    void testCadstrarElemento() {
        cadastro.cadastrarElemento("Cadeira");
        List<String> elementos = cadastro.getElementos();
        assertNotNull(elementos);
        assertEquals(1, elementos.size());
        assertEquals("Cadeira", elementos.get(0));
    }

    @Test
    @Order(2)
    @DisplayName("Deveria editar um elemento já cadastrado")
    void testEditarElement() {
        cadastro.cadastrarElemento("Cadeira");
        cadastro.editarElemento(0, "Cadeira de Balanço");
        List<String> elementos = cadastro.getElementos();
        assertEquals(1, elementos.size());
        assertEquals("Cadeira de Balanço", elementos.get(0));
    }
}
