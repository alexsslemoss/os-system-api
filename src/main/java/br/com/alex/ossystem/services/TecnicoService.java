package br.com.alex.ossystem.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alex.ossystem.domain.Pessoa;
import br.com.alex.ossystem.domain.Tecnico;
import br.com.alex.ossystem.domain.dtos.TecnicoDTO;
import br.com.alex.ossystem.exceptions.DataIntegratyViolationException;
import br.com.alex.ossystem.exceptions.DataNotFoundException;
import br.com.alex.ossystem.repositories.PessoaRepository;
import br.com.alex.ossystem.repositories.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public TecnicoDTO findById(Integer id) {
		return this.tecnicoRepository.findById(id)
				.map(TecnicoDTO::new)
				.orElseThrow(DataNotFoundException::new);
	}

	public List<TecnicoDTO> findAll() {
		return this.tecnicoRepository.findAll().stream().map(TecnicoDTO::new).collect(Collectors.toList());
	}

	public TecnicoDTO create(TecnicoDTO tecnicoDTO) {
		Optional<Pessoa> optional = findByCpf(tecnicoDTO.getCpf());

		if (optional.isPresent()) {
			throw new DataIntegratyViolationException();
		}

		Tecnico tecnico = this.tecnicoRepository
				.save(new Tecnico(null, tecnicoDTO.getNome(), tecnicoDTO.getCpf(), tecnicoDTO.getTelefone()));
		return new TecnicoDTO(tecnico);
	}

	public TecnicoDTO update(Integer id, TecnicoDTO tecnicoDTO) {
		Optional<Pessoa> optional = findByCpf(tecnicoDTO.getCpf());

		if (optional.isPresent() && !optional.get().getId().equals(id)) {
			throw new DataIntegratyViolationException();
		}

		Tecnico tecnico = (Tecnico) optional.get();
		tecnico.setNome(tecnicoDTO.getNome());
		tecnico.setCpf(tecnicoDTO.getCpf());
		tecnico.setTelefone(tecnicoDTO.getTelefone());

		return new TecnicoDTO(this.tecnicoRepository.save(tecnico));

	}

	public void delete(Integer id) {
		Tecnico tecnico = this.tecnicoRepository.findById(id).orElseThrow(DataNotFoundException::new);

		if (!tecnico.getList().isEmpty()) {
			throw new DataIntegratyViolationException("O Técnico possui Ordem de Serviço, não pode ser excluído");
		}

		this.tecnicoRepository.deleteById(id);
	}

	private Optional<Pessoa> findByCpf(String cpf) {
		return this.pessoaRepository.findByCpf(cpf);

	}

}
