package br.com.alex.ossystem.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alex.ossystem.domain.dtos.OrdemServicoDTO;
import br.com.alex.ossystem.services.OrdemServicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/ordem-servico")
public class OrdemServicoResource {

	@Autowired
	private OrdemServicoService ordemServicoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Integer id) {
		OrdemServicoDTO ordemServicoDTO = this.ordemServicoService.findById(id);
		return ResponseEntity.ok(ordemServicoDTO);
	}

	@GetMapping
	public ResponseEntity<List<OrdemServicoDTO>> findAll() {
		List<OrdemServicoDTO> ordemServicoDTOs = this.ordemServicoService.findAll();
		return ResponseEntity.ok(ordemServicoDTOs);
	}
	
	@PostMapping
	public ResponseEntity<OrdemServicoDTO> create(@Valid @RequestBody OrdemServicoDTO ordemServico) {
		OrdemServicoDTO ordemServicoDTO = this.ordemServicoService.create(ordemServico);
		return ResponseEntity.ok(ordemServicoDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> update(@PathVariable Integer id, @Valid @RequestBody OrdemServicoDTO ordemServicoDTO) {
		OrdemServicoDTO dto = this.ordemServicoService.update(id, ordemServicoDTO);
		return ResponseEntity.ok(dto);
	}

//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<Void> delete(@PathVariable Integer id) {
//		this.ordemServicoService.delete(id);
//		return ResponseEntity.noContent().build();
//	}
	
}
