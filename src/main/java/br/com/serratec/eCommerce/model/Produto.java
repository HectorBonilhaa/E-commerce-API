package br.com.serratec.eCommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;

	@Column(nullable = false)
	private String nome;

	private String descricao;

	@Column(name = "qtd_estoque", nullable = false)
	private Integer qtdEstoque;
	
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	@Column(name = "valor_unitario", nullable = false)
	private Float valorUnitario;
	
	private Byte imagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Float getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Byte getImagem() {
		return imagem;
	}

	public void setImagem(Byte imagem) {
		this.imagem = imagem;
	}
	
//	 ID_CATEGORIA CHAVE ESTRANGEIRA
	
	
	
}
