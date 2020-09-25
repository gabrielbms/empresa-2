package br.com.contmatic.telefone;

import static br.com.contmatic.telefone.TelefoneDDDType.DDD11;
import static br.com.contmatic.telefone.TelefoneType.CELULAR;
import static br.com.contmatic.telefone.TelefoneType.FIXO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class TelefoneTest.
 * 
 * @author gabriel.santos
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TelefoneTest {

	/** The telefone. */
	private static Telefone telefone;

	/**
	 * Inicio dos testes.
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
		telefone = Fixture.from(Telefone.class).gimme("valido");
	}

	@Test
	public void deve_testar_se_o_numero_aceita_numeros() {
		telefone.setNumero("937018888");
		assertEquals("937018888", telefone.getNumero());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_null_no_numero() {
		telefone.setNumero(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_vazio_no_numero() {
		telefone.setNumero("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espacos_em_branco_no_numero() {
		telefone.setNumero("  ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_letras_no_numero() {
		telefone.setNumero("abcdefabcde");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_caracteres_especiais_no_numero() {
		telefone.setNumero("@#$");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espaco_no_inicio_do_numero() {
		telefone.setNumero(" 43701888817");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espaco_no_final_do_numero() {
		telefone.setNumero("43701888817 ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espacos_no_meio_do_numero() {
		telefone.setNumero("437018      88817");
	}

	@Test
	public void deve_testar_o_setNumero() {
		telefone.setNumero("437018888");
		assertEquals("437018888", telefone.getNumero());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_menor() {
		telefone.setNumero("7777777");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_maior() {
		telefone.setNumero("11111111111");
	}

	@Test
	public void deve_testar_o_getNumero_esta_funcionando_corretamente() {
		telefone.setNumero("946756084");
		assertEquals("946756084", telefone.getNumero());
	}

	@Test
	public void deve_testar_se_o_valida_tipo_telefone_esta_funcionando() {
		telefone.setNumero("27219689");
		assertEquals(FIXO, telefone.getTipoTelefone());
	}

	@Test
	public void deve_retornar_true_no_hashCode_com_telefones_iguais() {
		Telefone telefone1 = new Telefone(DDD11, "978457845", CELULAR);
		Telefone telefone2 = new Telefone(DDD11, "978457845", CELULAR);
		assertEquals(telefone1.hashCode(), telefone2.hashCode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_hashCode_com_um_telefone_de_numero_null() {
		Telefone outroTelefone = new Telefone(DDD11, null, CELULAR);
		assertNotEquals(telefone.hashCode(), outroTelefone.hashCode());
	}

	@Test
	public void deve_retornar_true_no_equals_com_telefones_iguais() {
		Telefone telefone1 = new Telefone(DDD11, "978457845", CELULAR);
		Telefone telefone2 = new Telefone(DDD11, "978457845", CELULAR);
		assertEquals(telefone1, telefone2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_equals_com_um_telefone_de_numero_null() {
		Telefone outroTelefone = new Telefone(DDD11, null, CELULAR);
		assertNotEquals(telefone, outroTelefone);
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_um_telefone_com_ele_mesmo() {
		assertEquals(telefone, telefone);
	}

	@Test
	public void deve_retornar_false_no_equals_comparando_um_telefone_com_null() {
		assertNotEquals(telefone, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_true_no_equals_comparando_dois_telefone_de_numero_null() {
		Telefone telefone1 = new Telefone(DDD11, null, CELULAR);
		Telefone telefone2 = new Telefone(DDD11, null, CELULAR);
		assertEquals(telefone1, telefone2);
	}

	@Test
	public void deve_retornar_false_no_equals_com_telefone_de_numeros_diferentes() {
		Telefone outroTelefone = new Telefone(DDD11, "978457857", CELULAR);
		assertNotEquals(telefone, outroTelefone);
	}

	@Test
	public void deve_retornar_false_no_equals_com_telefone_e_um_objeto_aleatorio() {
		assertNotEquals(telefone, new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void toString_deve_retornar_null() {
		Telefone telefoneNull = new Telefone(null, null, null);
		String telefoneNullToString = telefoneNull.toString();
		assertEquals(telefoneNull.toString(), telefoneNullToString);
	}

	@Test
	public void toString_deve_retornar_preenchido() {
		String telefoneToString = telefone.toString();
		assertEquals(telefoneToString, telefone.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_tentar_setar_o_contrutor_null() {
		Telefone outroTelefone = new Telefone(null, null, null);
		assertEquals(null, outroTelefone);
	}

	@Test
	public void deve_testar_o_getDdd_do_TelefoneDDDType() {
		TelefoneDDDType telefoneDDD = DDD11;
		assertEquals(11, telefoneDDD.getDdd());

	}

	@Test
	public void deve_testar_o_getComplemento_do_TelefoneDDDType() {
		TelefoneDDDType telefoneDddComplemento = DDD11;
		assertEquals("São Paulo – SP", telefoneDddComplemento.getComplemento());
	}

	@Test
	public void deve_testar_o_getDescricao_do_TipoTelefoneType() {
		TelefoneType telefoneDescricao = CELULAR;
		assertEquals("Celular", telefoneDescricao.getDescricao());

	}

	@Test
	public void deve_testar_o_getTamanho_do_TipoTelefoneType() {
		TelefoneType telefoneDescricao = CELULAR;
		assertEquals(9, telefoneDescricao.getTamanho());
	}
	
	@Test
	public void deve_testar_o_getDdd() {
		telefone.setDdd(DDD11);
		assertEquals(DDD11, telefone.getDdd());
	}

	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
	}

	/**
	 * Teste no to string.
	 */
	@AfterClass
	public static void teste_no_toString() {
	}

}
