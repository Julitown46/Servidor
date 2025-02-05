package org.iesbelen.modelo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesbelen.validator.RangoCategoriaPlusValidation;
import org.iesbelen.validator.RangoCategoriaValidation;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	
	private long id;

	@NotBlank(message = "{error}")
	@Size(max=30, message = "{error}")
	private String nombre;

	@NotBlank(message = "{error}")
	@Size(max=30, message = "{error}")
	private String apellido1;

	@NotBlank(message = "{error}")
	@Size(max=30, message = "{error}")
	private String apellido2;

	@NotBlank(message = "{error}")
	@Size(max=50, message = "{error}")
	private String ciudad;

	@NotNull(message = "Por favor, introduzca categoria.")
	@RangoCategoriaValidation
	private int categoria;

//	@Email(message = "Formato de email incorrecto", regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
//	//@NotBlank(message = "Por favor, introduzca email.")
//	private String email;

}
