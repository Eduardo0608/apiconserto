package ifsp.pw3s4.apiconserto.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Conserto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Data deve estar no formato dd/MM/yyyy")
    private String dataEntrada;

    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Data deve estar no formato dd/MM/yyyy")
    private String dataSaida;

    @Embedded
    @Valid
    private Mecanico mecanico;

    @Embedded
    @Valid
    private Veiculo veiculo;

    @Column(nullable = false)
    private Boolean ativo = true;
}