package med.voll.apirest.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.apirest.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
		
		@NotNull
		Long id,
		
		String nome,
		String telefone,
		DadosEndereco endereco
		) {

}
