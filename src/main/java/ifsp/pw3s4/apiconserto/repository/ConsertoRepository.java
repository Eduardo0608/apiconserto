package ifsp.pw3s4.apiconserto.repository;

import ifsp.pw3s4.apiconserto.dto.ConsertoResumoDTO;
import ifsp.pw3s4.apiconserto.model.Conserto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsertoRepository extends JpaRepository<Conserto, Long> {

    List<Conserto> findByAtivoTrue();

    @Query("SELECT new ifsp.pw3s4.apiconserto.dto.ConsertoResumoDTO(c.id, c.dataEntrada, c.dataSaida, c.mecanico.nome, c.veiculo.marca, c.veiculo.modelo) FROM Conserto c WHERE c.ativo = true")
    List<ConsertoResumoDTO> findResumoAtivos();
}