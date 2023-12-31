package med.voll.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.apirest.medico.DadosDetalhamentoMedico;
import med.voll.apirest.paciente.DadosAtualizacaoPaciente;
import med.voll.apirest.paciente.DadosCadastroPaciente;
import med.voll.apirest.paciente.DadosDetalhamentoPaciente;
import med.voll.apirest.paciente.DadosListagemPaciente;
import med.voll.apirest.paciente.Paciente;
import med.voll.apirest.paciente.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
	
	@Autowired
	private PacienteRepository repository;
	
	@PostMapping
	@Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
       var paciente = new Paciente(dados);
       repository.save(paciente);
       var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
	   return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }
	
	@GetMapping
	public ResponseEntity <Page<DadosListagemPaciente>> listar(@PageableDefault(page = 0, size = 10, sort = { "nome" }) Pageable paginacao) {
	    var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
	    return ResponseEntity.ok(page);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
		var paciente = repository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
	    var paciente = repository.getReferenceById(id);
	    paciente.inativar();
	    
	    return ResponseEntity.noContent().build();
	}
}
