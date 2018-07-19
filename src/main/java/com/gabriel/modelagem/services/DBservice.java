package com.gabriel.modelagem.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.gabriel.modelagem.domain.enums.Perfil;
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

@Service
public class DBservice {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
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
	
	public void intantiateTestDatabase( ) throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa", 80.00);
		Produto p5 = new Produto(null, "Toalha", 80.00);
		Produto p6 = new Produto(null, "Colcha", 80.00);
		Produto p7 = new Produto(null, "TV", 80.00);
		Produto p8 = new Produto(null, "Cortador", 80.00);
		Produto p9 = new Produto(null, "Abajour", 80.00);
		Produto p10 = new Produto(null, "Pendente", 80.00);
		Produto p11 = new Produto(null, "Shampoo", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
						
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null, "Bahia");
		Estado est2 = new Estado(null, "Minas Gerais");
		
		Cidade cid1 = new Cidade(null, "Salvador", est1);
		Cidade cid2 = new Cidade(null, "Uberaba", est2);
		Cidade cid3 = new Cidade(null, "Patos de Minas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "gabrielmorais96@gmail.com", "4571256568", TipoCliente.PESSOAFISICA, pe.encode("batata"));
		cli1.getTelefones().addAll(Arrays.asList("5985656666", "9984551255"));
		
		Cliente cli2 = new Cliente(null, "Paulo Silva", "gabrielmorais964@gmail.com", "24429137129", TipoCliente.PESSOAFISICA, pe.encode("batata"));
		cli2.addPerfil(Perfil.ADMIN);
		cli2.getTelefones().addAll(Arrays.asList("984545876", "9845498165"));
		
		
		Endereco ed1 = new Endereco(null, "Rua F", "405", "Apto 405", "Plano Piloto", "0548874212", cli1, cid1);
		Endereco ed2 = new Endereco(null, "Rua J", "452", "Apto 445", "Centro", "598954979", cli1, cid2);
		Endereco ed3 = new Endereco(null, "Rua H", "452", "Apto 987", "Centro", "598954979", cli2, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(ed1, ed2));
		cli2.getEnderecos().addAll(Arrays.asList(ed3));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(ed1, ed2, ed3));
		
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
