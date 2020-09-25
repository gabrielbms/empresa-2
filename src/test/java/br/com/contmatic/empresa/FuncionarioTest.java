package br.com.contmatic.empresa;

import static br.com.contmatic.telefone.TelefoneDDDType.DDD11;
import static br.com.contmatic.telefone.TelefoneType.CELULAR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class FuncionarioTest.
 * 
 * @author gabriel.santos
 */
@FixMethodOrder(NAME_ASCENDING)
public class FuncionarioTest {

	/** The funcionario. */
	private static Funcionario funcionario;
	
	private Telefone telefone;

	private Set<Telefone> telefones = new HashSet<>();
	
	private Endereco endereco;
	
	private Set<Endereco> enderecos = new HashSet<>();


	/**
	 * Set up before class.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		FixtureFactoryLoader.loadTemplates("br.com.contmatic.util");
	}

	/**
	 * Set up.
	 */
	@Before
	public void setUp() {
		funcionario = Fixture.from(Funcionario.class).gimme("valido");
		telefone = new Telefone(DDD11, "978457845", CELULAR);
		telefones.add(telefone);
		endereco = new Endereco("03208070", 79);
		enderecos.add(endereco);
	}
	
	@Test
	public void deve_testar_se_o_cpf_aceita_numeros() {
		funcionario.setCpf("43701888817");
		assertEquals("43701888817", funcionario.getCpf());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_null_no_cpf() {
		funcionario.setCpf(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_vazio_no_cpf() {
		funcionario.setCpf("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espacos_em_branco_no_cpf() {
		funcionario.setCpf("  ");
	}

	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_letras_no_cpf() {
		funcionario.setCpf("abcdefabcde");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_caracteres_especiais_no_cpf() {
		funcionario.setCpf("@#$");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espaco_no_inicio_do_cpf() {
		funcionario.setCpf(" 43701888817");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espaco_no_final_do_cpf() {
		funcionario.setCpf("43701888817 ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espacos_no_meio_do_cpf() {
		funcionario.setCpf("437018      88817");
	}

	@Test
	public void deve_testar_o_setCpf() {
		funcionario.setCpf("43701888817");
		assertEquals("43701888817", funcionario.getCpf());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_menor() {
		funcionario.setCpf("1010101010");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_maior() {
		funcionario.setCpf("121212121212");
	}

	@Test(expected = IllegalStateException.class)
	public void deve_testar_exception_a_validação_do_cpf() {
		funcionario.setCpf("43701888818");
	}

	@Test
	public void deve_testar_se_o_nome_aceita_letras() {
		funcionario.setNome("Gabriel");
		assertEquals("Gabriel", funcionario.getNome());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_null_no_nome() {
		funcionario.setNome(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_vazio_no_nome() {
		funcionario.setNome("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espacos_em_branco_no_nome() {
		funcionario.setNome("          ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_numeros_no_nome() {
		funcionario.setNome("123456");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_caracteres_especiais_no_nome() {
		funcionario.setNome("@#$");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espaco_no_inicio_do_nome() {
		funcionario.setNome(" Gabriel");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espaco_no_final_do_nome() {
		funcionario.setNome("Gabriel ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espacos_no_meio_do_nome() {
		funcionario.setNome("Gabriel         Bueno");
	}

	@Test
	public void deve_testar_se_o_nome_aceita_um_espaco_entre_as_palavras() {
		funcionario.setNome("Gabriel Bueno");
		assertEquals("Gabriel Bueno", funcionario.getNome());
	}

	@Test
	public void deve_testar_o_setNome() {
		funcionario.setNome("Gabriel Bueno");
		assertEquals("Gabriel Bueno", funcionario.getNome());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_menor() {
		funcionario.setNome("a");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_maior() {
		funcionario.setNome("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcaabcabcabcabcabcaabcabcabc"
				+ "abcabcaabcabcabcabcabcabcabcabcabcabcabxc");
	}

	@Test
	public void deve_testar_o_getIdade() {
		funcionario.setIdade(20);
		assertSame(funcionario.getIdade(), 20);
	}

	@Test
	public void deve_testar_o_getTelefone() {
		funcionario.setTelefones(telefones);
		assertEquals(funcionario.getTelefone(), telefones);
	}

	@Test
	public void deve_testar_o_getEndereco() {
		funcionario.setEnderecos(enderecos);
		assertEquals(funcionario.getEndereco(), enderecos);
	}

	@Test
	public void deve_testar_o_getSalario() {
		funcionario.setSalario(BigDecimal.valueOf(1700.00));
		assertEquals(funcionario.getSalario(),  BigDecimal.valueOf(1700.00));
	}

	@Test
	public void deve_retornar_true_no_hashCode_com_funcionarios_iguais() {
		Funcionario funcionario2 = funcionario;
		assertEquals(funcionario.hashCode(), funcionario2.hashCode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_hashCode_com_um_funcionario_de_cpf_null() {
		Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertNotEquals(funcionario.hashCode(), funcionario2.hashCode());
	}

	@Test
	public void deve_retornar_true_no_equals_com_funcionarios_iguais() {
		Funcionario funcionario2 = funcionario;
		assertTrue(funcionario.equals(funcionario2) & funcionario2.equals(funcionario));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_equals_com_um_funcionario_de_cpf_null() {
		Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertFalse(funcionario.equals(funcionario2) & funcionario2.equals(funcionario));
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_um_funcionario_com_ele_mesmo() {
		assertEquals(funcionario, funcionario);
	}

	@Test
	public void deve_retornar_false_no_equals_comparando_um_funcionarios_com_null() {
		assertNotEquals(funcionario, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_true_no_equals_comparando_dois_funcionarios_de_cpf_null() {
		Funcionario funcionario1 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertEquals(funcionario1, funcionario2);
	}

	@Test
	public void deve_retornar_false_no_equals_com_funcionarios_de_cpf_diferentes() {
		Funcionario funcionario1 = new Funcionario("99074424880", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		Funcionario funcionario2 = new Funcionario("87749387897", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertNotEquals(funcionario2, funcionario1);
	}

	@Test
	public void deve_retornar_false_no_equals_com_funcionario_e_um_numero_aleatorio() {
		assertNotEquals(funcionario, new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void toString_deve_retornar_null() {
		funcionario = new Funcionario(null, null, 0, null, null, new BigDecimal("1"));
		String funcionarioToString = funcionario.toString();
		assertEquals(funcionario.toString(), funcionarioToString);
	}

	@Test
	public void toString_deve_retornar_preenchido() {
		String funcionarioToString = funcionario.toString();
		assertEquals(funcionario.toString(), funcionarioToString);
	}

	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
	}

	/**
	 * Tear down after class.
	 */
	@AfterClass
	public static void tearDownAfterClass() {
	}

}