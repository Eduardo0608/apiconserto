package ifsp.pw3s4.apiconserto.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Embeddable
public class Mecanico {

    @NotBlank(message = "Nome do mecânico é obrigatório")
    private String nome;

    private String anosDeExperiencia;
}