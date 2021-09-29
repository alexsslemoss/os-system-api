package br.com.alex.ossystem.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alex.ossystem.domain.Cliente;
import br.com.alex.ossystem.domain.Pessoa;
import br.com.alex.ossystem.domain.dtos.ClienteDTO;
import br.com.alex.ossystem.exceptions.DataIntegratyViolationException;
import br.com.alex.ossystem.exceptions.DataNotFoundException;
import br.com.alex.ossystem.repositories.ClienteRepository;
import br.com.alex.ossystem.repositories.PessoaRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public ClienteDTO findById(Integer id) {
		return this.clienteRepository.findById(id)
				.map(ClienteDTO::new)
				.orElseThrow(DataNotFoundException::new);
	}

	public List<ClienteDTO> findAll() {
		return this.clienteRepository.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
	}

	public ClienteDTO create(ClienteDTO clienteDTO) {
		Optional<Pessoa> optional = findByCpf(clienteDTO.getCpf());

		if (optional.isPresent()) {
			throw new DataIntegratyViolationException();
		}

		Cliente cliente = this.clienteRepository
				.save(new Cliente(null, clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getTelefone()));
		return new ClienteDTO(cliente);
	}

	public ClienteDTO update(Integer id, ClienteDTO clienteDTO) {
		Optional<Pessoa> optional = findByCpf(clienteDTO.getCpf());

		if (optional.isPresent() && !optional.get().getId().equals(id)) {
			throw new DataIntegratyViolationException();
		}

		Cliente cliente = (Cliente) optional.get();
		cliente.setNome(clienteDTO.getNome());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setTelefone(clienteDTO.getTelefone());

		return new ClienteDTO(this.clienteRepository.save(cliente));

	}

	public void delete(Integer id) {
		Cliente cliente = this.clienteRepository.findById(id).orElseThrow(DataNotFoundException::new);

		if (!cliente.getList().isEmpty()) {
			throw new DataIntegratyViolationException("O Técnico possui Ordem de Serviço, não pode ser excluído");
		}

		this.clienteRepository.deleteById(id);
	}

	private Optional<Pessoa> findByCpf(String cpf) {
		return this.pessoaRepository.findByCpf(cpf);

	}

}
