package ifsp.pw3s4.apiconserto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsertoResumoDTO {
    private Long id;
    private String dataEntrada;
    private String dataSaida;
    private String nomeMecanico;
    private String marcaVeiculo;
    private String modeloVeiculo;
}