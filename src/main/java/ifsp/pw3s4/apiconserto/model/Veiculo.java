package ifsp.pw3s4.apiconserto.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Embeddable
public class Veiculo {

    @NotBlank(message = "Marca do veículo é obrigatória")
    private String marca;

    @NotBlank(message = "Modelo do veículo é obrigatório")
    private String modelo;

    @Pattern(regexp = "\\d{4}", message = "Ano do veículo deve estar no formato 'xxxx'")
    private String ano;

    private String cor;
}