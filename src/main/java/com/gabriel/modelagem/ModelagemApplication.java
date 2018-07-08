package com.gabriel.modelagem;

import java.text.SimpleDateFormat;
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
import com.gabriel.modelagem.domain.ItemPedido;
import com.gabriel.modelagem.domain.Pagamento;
import com.gabriel.modelagem.domain.PagamentoComBoleto;
import com.gabriel.modelagem.domain.PagamentoComCartao;
import com.gabriel.modelagem.domain.Pedido;
import com.gabriel.modelagem.domain.Produto;
import com.gabriel.modelagem.domain.enums.EstadoPagamento;
import com.gabriel.modelagem.domain.enums.TipoCliente;
import com.gabriel.modelagem.repositories.CategoriaRepository;
import com.gabriel.modelagem.repositories.CidadeRepository;
import com.gabriel.modelagem.repositories.ClienteRepository;
import com.gabriel.modelagem.repositories.EnderecoRepository;
import com.gabriel.modelagem.repositories.EstadoRepository;
import com.gabriel.modelagem.repositories.ItemPedidoRepository;
import com.gabriel.modelagem.repositories.PagamentoRepository;
import com.gabriel.modelagem.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("15/08/2018 14:00"), cli1, ed1);
		Pedido ped2 = new Pedido(null, sdf.parse("20/08/2018 14:05"), cli1, ed2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2018 00:55"), null);		
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
			
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
	}
}














