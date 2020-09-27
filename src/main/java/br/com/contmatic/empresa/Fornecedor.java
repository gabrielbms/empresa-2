package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.CNPJ_INCORRETO;
import static br.com.contmatic.util.Constantes.CNPJ_INVALIDO;
import static br.com.contmatic.util.Constantes.CNPJ_SIZE;
import static br.com.contmatic.util.Constantes.CNPJ_VAZIO;
import static br.com.contmatic.util.Constantes.CPF_INVALIDO;
import static br.com.contmatic.util.Constantes.ENDERECO_QTDE_MAX;
import static br.com.contmatic.util.Constantes.ENDERECO_QTDE_MINIMA;
import static br.com.contmatic.util.Constantes.ENDERECO_SIZE_MAX;
import static br.com.contmatic.util.Constantes.ENDERECO_VAZIO;
import static br.com.contmatic.util.Constantes.NOME_INCORRETO;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.NOME_TAMANHO;
import static br.com.contmatic.util.Constantes.NOME_VAZIO;
import static br.com.contmatic.util.Constantes.PRODUTO_INCORRETO;
import static br.com.contmatic.util.Constantes.PRODUTO_INVALIDO;
import static br.com.contmatic.util.Constantes.PRODUTO_VAZIO;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CNPJ_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CNPJ_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TELEFONE_QTDE_MAX;
import static br.com.contmatic.util.Constantes.TELEFONE_QTDE_MINIMA;
import static br.com.contmatic.util.Constantes.TELEFONE_SIZE_MAX;
import static br.com.contmatic.util.Constantes.TELEFONE_VAZIO;
import static br.com.contmatic.util.RegexType.LETRAS;
import static br.com.contmatic.util.RegexType.NUMEROS;
import static br.com.contmatic.util.RegexType.validaSeNaoTemEspacosIncorretosECaracteresEspeciaos;
import static br.com.contmatic.util.Validate.isNotCNPJ;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import com.google.common.base.Preconditions;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Fornecedor.
 * 
 * @author gabriel.santos
 */
public class Fornecedor {

	/** The cnpj. */
	@CNPJ(message = CNPJ_INVALIDO)
	@NotBlank(message = CNPJ_VAZIO)
	@Pattern(regexp = NUMEROS, message = CNPJ_INCORRETO)
	private String cnpj;

	/** The nome. */
	@NotBlank(message = NOME_VAZIO)
	@Pattern(regexp = LETRAS, message = NOME_INCORRETO)
	@Size(min = 2, max = 100, message = NOME_TAMANHO)
	private String nome;

	/** The produto. */
	@NotBlank(message = PRODUTO_VAZIO)
	@Length(min = 2, max = 80, message = PRODUTO_INCORRETO)
	@Pattern(regexp = LETRAS	, message = PRODUTO_INVALIDO)
	private Set<Produto> produto;

	/** The telefones. */
	@Valid
	@NotEmpty(message = TELEFONE_VAZIO)
	@Size.List({ @Size(min = 1, message = TELEFONE_QTDE_MINIMA),
			@Size(max = 3, message = TELEFONE_QTDE_MAX) })
	private Set<Telefone> telefones;

	/** The enderecos. */
	@Valid
	@NotEmpty(message = ENDERECO_VAZIO)
	@Size.List({ @Size(min = 1, message = ENDERECO_QTDE_MINIMA),
			@Size(max = 3, message =ENDERECO_QTDE_MAX) })
	private Set<Endereco> enderecos;

	/**
	 * Instantiates a new fornecedor.
	 *
	 * @param cnpj the cnpj
	 * @param nome the nome
	 */
	public Fornecedor(String cnpj, String nome) {
		this.setCnpj(cnpj);
		this.setNome(nome);
	}
	
	/**
	 * Instantiates a new fornecedor.
	 *
	 * @param cnpj the cnpj
	 * @param nome the nome
	 * @param telefone the telefones
	 * @param produto the produto
	 * @param endereco the enderecos
	 */
	public Fornecedor(String cnpj, String nome, Set<Telefone> telefone, Set<Produto> produto, Set<Endereco> endereco) {
		this.setCnpj(cnpj);
		this.setNome(nome);
		this.setTelefones(telefone);
		this.setProduto(produto);
		this.setEnderecos(endereco);
	}

	/**
	 * Instantiates a new fornecedor.
	 */
	public Fornecedor() {
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.validaCnpjIncorreto(cnpj);
		this.validaCnpjInvalido(cnpj);
		this.validaEspacosIncorretosECaracteresEspeciais(cnpj);
		this.cnpj = cnpj;
	}
	
	private void validaEspacosIncorretosECaracteresEspeciais(String cnpj) {
		if (validaSeNaoTemEspacosIncorretosECaracteresEspeciaos(cnpj)) {
			throw new IllegalArgumentException(CPF_INVALIDO);
		}
	}
	
	private void validaCnpjInvalido(String cnpj) {
		if (isNotCNPJ(cnpj)) {
			throw new IllegalStateException(CNPJ_INVALIDO);
		}
	}
	
	private void validaCnpjIncorreto(String cnpj) {
		this.validaCnpjNulloOuVazio(cnpj);
		this.validaCnpjComTamanhoMenor(cnpj);
		this.validaCnpjComTamanhoMaior(cnpj);
	}

	private void validaCnpjComTamanhoMaior(String cnpj) {
		if (cnpj.length() > CNPJ_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CNPJ_GRANDE_DEMAIS);
		}
	}

	private void validaCnpjComTamanhoMenor(String cnpj) {
		if (cnpj.length() < CNPJ_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CNPJ_PEQUENO_DEMAIS);
		}
	}

	private void validaCnpjNulloOuVazio(String cnpj) {
		if (cnpj == null || cnpj.trim().isEmpty()) {
			throw new IllegalArgumentException(CNPJ_VAZIO);
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.validaNomeIncorreto(nome);
		this.validaEspacosIncorretosECaracteresEspeciaisNoNome(nome);
		this.nome = nome;
	}
	
	private void validaEspacosIncorretosECaracteresEspeciaisNoNome(String nome) {
		if (validaSeNaoTemEspacosIncorretosECaracteresEspeciaos(nome)) {
			throw new IllegalArgumentException(NOME_INVALIDO);
		}
	}
	
	private void validaNomeIncorreto(String nome) {
		this.validaNomeNulloOuVazio(nome);
		this.validaNomeMenorQueOTamanhoMinimo(nome);
		this.validaNomeMaiorQueOTamanhoMinimo(nome);
	}

	private void validaNomeMaiorQueOTamanhoMinimo(String nome) {
		if (nome.length() > NOME_MAX_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_NOME_GRANDE_DEMAIS);
		}
	}

	private void validaNomeMenorQueOTamanhoMinimo(String nome) {
		if (nome.length() < NOME_MIN_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_NOME_PEQUENO_DEMAIS);
		}
	}

	private void validaNomeNulloOuVazio(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException(NOME_VAZIO);
		}
	}

	public Set<Produto> getProduto() {
		return produto;
	}

	public void setProduto(Set<Produto> produto) {
		this.validaProdutoNullo(produto);
		this.produto = produto;
	}

	private void validaProdutoNullo(Set<Produto> produto) {
		if (produto == null) {
			throw new IllegalArgumentException(PRODUTO_VAZIO);
		}
	}

	public @Valid Set<Telefone> getTelefone() {
		return telefones;
	}
	
	public void setTelefones(Set<Telefone> telefone) {
		Preconditions.checkArgument(telefone.size() < 2, TELEFONE_SIZE_MAX);
		this.validaTelefoneNullo(telefone);
		this.telefones = telefone;
	}
	
	private void validaTelefoneNullo(Set<Telefone> telefone) {
		if (telefone == null) {
			throw new IllegalArgumentException(TELEFONE_VAZIO);
		}
	}

	public @Valid Set<Endereco> getEndereco() {
		return enderecos;
	}
	
	public void setEnderecos(Set<Endereco> endereco) {
		Preconditions.checkArgument(endereco.size() < 2, ENDERECO_SIZE_MAX);
		this.validaEnderecoNullo(endereco);
		this.enderecos = endereco;
	}
	
	private void validaEnderecoNullo(Set<Endereco> endereco) {
		if (endereco == null) {
			throw new IllegalArgumentException(ENDERECO_VAZIO);
		}
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
