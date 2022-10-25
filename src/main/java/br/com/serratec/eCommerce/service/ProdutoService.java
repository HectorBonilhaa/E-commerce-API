package br.com.serratec.eCommerce.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.eCommerce.dto.ProdutoRequestDTO;
import br.com.serratec.eCommerce.dto.ProdutoResponseDTO;
import br.com.serratec.eCommerce.exception.ResourceBadRequestException;
import br.com.serratec.eCommerce.exception.ResourceNotFoundException;
import br.com.serratec.eCommerce.model.Produto;
import br.com.serratec.eCommerce.repository.ProdutoRepository;


@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repositorio;

	
	private ModelMapper mapper = new ModelMapper(); 
	
	// IMAGEM


	
	private String raiz = "E:\\Meus Documentos\\Documentos\\workspace\\E-commerce-API\\src\\main\\java\\br\\com\\serratec\\eCommerce";

	//diretorio das fotos
    private String diretorioFotos = "fotos";

	public void salvarFoto(MultipartFile foto){
        this.salvar(this.diretorioFotos, foto);
    }

	public void salvar(String diretorio,MultipartFile arquivo){ // diretorio especifico, // arquivo 

		//diretorio
		Path diretorioPath = Paths.get(this.raiz, diretorio); // raiz + diretorio especifico
		
		// diretorio + arquivo 
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());

		try{
			Files.createDirectories(diretorioPath); //cria diretorio
			arquivo.transferTo(arquivoPath.toFile()); // transfere arquivo para o path do arquivo
		}catch(IOException e){
			throw new RuntimeException("Problemas ao tentar salvar arquivo");
		}
	}

	//OBTER TODOS
	
	public List<ProdutoResponseDTO> obterTodos(){
		List<Produto> lista = repositorio.findAll();
		var novaLista = new ArrayList<ProdutoResponseDTO>();
		
		for(Produto produto : lista) {
			novaLista.add(mapper.map(produto, ProdutoResponseDTO.class));
		}
		return novaLista;
	}
	
	
	//OBTER POR ID
	
	public Optional<ProdutoResponseDTO> obterPorId(Long id){
		Optional<Produto> optProduto = repositorio.findById(id);
		
		if(optProduto.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possível encontrar a produto com id " + id);
		}
		
		ProdutoResponseDTO dto = mapper.map(optProduto.get(), ProdutoResponseDTO.class);
		
		return Optional.of(dto);
	}
	
	//CADASTRAR

	public ProdutoResponseDTO cadastrar(ProdutoRequestDTO produto) {
		validarNome(produto);
		validarDescricao(produto);
		validarQtdEstoque(produto);
		validarValorUnitario(produto);
		var produtoModel = mapper.map(produto, Produto.class);
		produtoModel.setId(null);
		produtoModel = repositorio.save(produtoModel);
		
		var response = mapper.map(produtoModel, ProdutoResponseDTO.class);
		return response;
	}
	
	//ATUALIZAR
	
	public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produto) {
		
		obterPorId(id);
		validarNome(produto);
		validarDescricao(produto);
		validarQtdEstoque(produto);
		validarValorUnitario(produto);
		
		var produtoModel = mapper.map(produto, Produto.class);
		
		produtoModel.setId(id);
		produtoModel =  repositorio.save(produtoModel);
		
		return mapper.map(produtoModel, ProdutoResponseDTO.class);
	}
	
	//DELETAR
	
	public void deletar(Long id) {

		obterPorId(id);
		repositorio.deleteById(id);
	}
	
	private void validarNome(ProdutoRequestDTO produto) {
		
		List<Produto> produtos = repositorio.findByNome(produto.getNome());
		if(produto.getNome() == null) {
			throw new ResourceBadRequestException("O nome do produto deve ser informado");
		}else if(produtos.size()>0) {
			throw new ResourceBadRequestException("Este produto já existe");	
		}
	}
	
	private void validarDescricao(ProdutoRequestDTO produto) {
		if(produto.getDescricao() == null) {
			throw new ResourceBadRequestException("A descrição do produto deve ser informada");
		}
	}
	
	private void validarQtdEstoque(ProdutoRequestDTO produto) {
		if(produto.getQuantidadeEstoque() == null) {
			throw new ResourceBadRequestException("A quantidade disponível em estoque deve ser informada");
		}
	}
	
	private void validarValorUnitario(ProdutoRequestDTO produto) {
		if(produto.getValorUnitario() < 1) {
			throw new ResourceBadRequestException("O valor unitário não pode ser menor que R$1,00");
		}
	}
	
	
	}
