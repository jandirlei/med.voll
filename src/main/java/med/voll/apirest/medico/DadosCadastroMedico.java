package med.voll.apirest.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.apirest.endereco.DadosEndereco;

public record DadosCadastroMedico(
		
		@NotBlank
		String nome,
		
		@NotBlank
		@Email
		String email,
		
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String crm,
		
		@NotNull
		Especialidade especialidade,
		
		String telefone,
		
		@NotNull
		@Valid
		DadosEndereco endereco) {
}
