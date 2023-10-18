package med.voll.apirest.paciente;

import jakarta.validation.Valid;
import med.voll.apirest.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
		Long id,
		String nome,
		String telefone,
		
		@Valid
		DadosEndereco endereco) {
}
