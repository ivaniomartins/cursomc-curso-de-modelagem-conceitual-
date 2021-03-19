package com.martinssystems.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.martinssystems.cursomc.domain.Categoria;
import com.martinssystems.cursomc.domain.Cidade;
import com.martinssystems.cursomc.domain.Cliente;
import com.martinssystems.cursomc.domain.Endereco;
import com.martinssystems.cursomc.domain.Estado;
import com.martinssystems.cursomc.domain.Pagamento;
import com.martinssystems.cursomc.domain.PagamentoComBoleto;
import com.martinssystems.cursomc.domain.PagamentoComCartao;
import com.martinssystems.cursomc.domain.Pedido;
import com.martinssystems.cursomc.domain.Produto;
import com.martinssystems.cursomc.domain.enums.EstadoPagamento;
import com.martinssystems.cursomc.domain.enums.TipoCliente;
import com.martinssystems.cursomc.repositories.CategoriaRepository;
import com.martinssystems.cursomc.repositories.CidadeRepository;
import com.martinssystems.cursomc.repositories.ClienteRepository;
import com.martinssystems.cursomc.repositories.EnderecoRepository;
import com.martinssystems.cursomc.repositories.EstadoRepository;
import com.martinssystems.cursomc.repositories.PagamentoRepository;
import com.martinssystems.cursomc.repositories.PedidoRepository;
import com.martinssystems.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minhas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "363978151212", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("8195787812", "871245232"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "54520710", cli1, c1);

		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Cortes", "541630175", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy hh:MM");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/07/2017 00:00"),
				null);
		ped2.setPagamento(pag2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

	}

}
