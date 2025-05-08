package ifsp.pw3s4.apiconserto.controller;

import ifsp.pw3s4.apiconserto.dto.ConsertoResumoDTO;
import ifsp.pw3s4.apiconserto.model.Conserto;
import ifsp.pw3s4.apiconserto.repository.ConsertoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository consertoRepository;

    @PostMapping
    public ResponseEntity<Conserto> criarConserto(@RequestBody @Valid Conserto conserto, UriComponentsBuilder uriBuilder) {
        Conserto salvo = consertoRepository.save(conserto);
        URI uri = uriBuilder.path("/consertos/{id}").buildAndExpand(salvo.getId()).toUri();
        return ResponseEntity.created(uri).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Conserto>> listarTodos() {
        return ResponseEntity.ok(consertoRepository.findByAtivoTrue());
    }

    @GetMapping("/resumo")
    public ResponseEntity<List<ConsertoResumoDTO>> listarResumo() {
        List<ConsertoResumoDTO> lista = consertoRepository.findByAtivoTrue().stream().map(c ->
                new ConsertoResumoDTO(
                        c.getId(),
                        c.getDataEntrada(),
                        c.getDataSaida(),
                        c.getMecanico().getNome(),
                        c.getVeiculo().getMarca(),
                        c.getVeiculo().getModelo()
                )).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conserto> buscarPorId(@PathVariable Long id) {
        return consertoRepository.findById(id)
                .filter(Conserto::getAtivo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conserto> atualizarConserto(@PathVariable Long id, @RequestBody @Valid Conserto atualizacao) {
        Optional<Conserto> existente = consertoRepository.findById(id);

        if (existente.isEmpty() || !existente.get().getAtivo()) {
            return ResponseEntity.notFound().build();
        }

        Conserto conserto = existente.get();

        conserto.setDataSaida(atualizacao.getDataSaida());

        conserto.getMecanico().setNome(atualizacao.getMecanico().getNome());
        conserto.getMecanico().setAnosDeExperiencia(atualizacao.getMecanico().getAnosDeExperiencia());

        conserto.getVeiculo().setMarca(atualizacao.getVeiculo().getMarca());
        conserto.getVeiculo().setModelo(atualizacao.getVeiculo().getModelo());
        conserto.getVeiculo().setAno(atualizacao.getVeiculo().getAno());
        conserto.getVeiculo().setCor(atualizacao.getVeiculo().getCor());

        return ResponseEntity.ok(consertoRepository.save(conserto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLogicamente(@PathVariable Long id) {
        Optional<Conserto> existente = consertoRepository.findById(id);

        if (existente.isEmpty() || !existente.get().getAtivo()) {
            return ResponseEntity.notFound().build();
        }

        Conserto conserto = existente.get();
        conserto.setAtivo(false);
        consertoRepository.save(conserto);

        return ResponseEntity.noContent().build();
    }
}