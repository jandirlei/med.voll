package med.voll.apirest.medico;

import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import med.voll.apirest.endereco.Endereco;

public record DadosDetalhamentoMedico(
		Long id,
		String nome,
		String email,
		String crm,
		Especialidade especialidade,
		String telefone,
		Endereco endereco) {
	
	public DadosDetalhamentoMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(),
				medico.getTelefone(), medico.getEndereco());
	}
}
