package br.com.alex.ossystem.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alex.ossystem.domain.Cliente;
import br.com.alex.ossystem.domain.OrdemServico;
import br.com.alex.ossystem.domain.Tecnico;
import br.com.alex.ossystem.domain.enums.Prioridade;
import br.com.alex.ossystem.domain.enums.Status;
import br.com.alex.ossystem.repositories.ClienteRepository;
import br.com.alex.ossystem.repositories.OrdemServicoRepository;
import br.com.alex.ossystem.repositories.TecnicoRepository;

@Service
public class BDService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	public void instanciaBD() {

		Tecnico t1 = new Tecnico(null, "Alex", "458.636.230-80", "(11) 5555-5555");
		Tecnico t2 = new Tecnico(null, "José", "843.041.070-89", "(11) 91234-54321");

		Cliente c1 = new Cliente(null, "Sandro", "043.071.150-66", "(11) 4444-4444");

		OrdemServico os1 = new OrdemServico(null, Prioridade.ALTA, "Teste de criação de Ordem de Serviço", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);

		c1.getList().add(os1);

		this.tecnicoRepository.saveAll(Arrays.asList(t1, t2));

		this.clienteRepository.saveAll(Arrays.asList(c1));

		this.ordemServicoRepository.saveAll(Arrays.asList(os1));

	}

}
