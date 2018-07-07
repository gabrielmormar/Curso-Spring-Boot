package com.gabriel.modelagem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabriel.modelagem.domain.Categoria;
import com.gabriel.modelagem.domain.Cidade;
import com.gabriel.modelagem.domain.Cliente;
import com.gabriel.modelagem.domain.Endereco;
import com.gabriel.modelagem.domain.Estado;
import com.gabriel.modelagem.domain.Produto;
import com.gabriel.modelagem.domain.enums.TipoCliente;
import com.gabriel.modelagem.repositories.CategoriaRepository;
import com.gabriel.modelagem.repositories.CidadeRepository;
import com.gabriel.modelagem.repositories.ClienteRepository;
import com.gabriel.modelagem.repositories.EnderecoRepository;
import com.gabriel.modelagem.repositories.EstadoRepository;
import com.gabriel.modelagem.repositories.ProdutoRepository;

@SpringBootApplication
public class ModelagemApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;	
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;	
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ModelagemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));		
						
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Bahia");
		Estado est2 = new Estado(null, "Minas Gerais");
		
		Cidade cid1 = new Cidade(null, "Salvador", est1);
		Cidade cid2 = new Cidade(null, "Uberaba", est2);
		Cidade cid3 = new Cidade(null, "Patos de Minas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "4571256568", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("5985656666", "9984551255"));
		
		Endereco ed1 = new Endereco(null, "Rua F", "405", "Apto 405", "Plano Piloto", "0548874212", cli1, cid1);
		Endereco ed2 = new Endereco(null, "Rua J", "452", "Apto 445", "Centro", "598954979", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(ed1, ed2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(ed1, ed2));
		
	}
}














