package one.digitalinnovation.gof.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description= "Retorna a lista de pessoa"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode  = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscaTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @Parameter(name = "Busca por Id", description = "Busca o cliente pelo Id")
    @GetMapping("/(id)")
    public ResponseEntity<Cliente> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public void salvar(@RequestBody Cliente cliente) {
        service.inserir(cliente);
        ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/(id)")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        service.atualizar(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/(id)")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
