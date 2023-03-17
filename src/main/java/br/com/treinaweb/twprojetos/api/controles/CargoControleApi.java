package br.com.treinaweb.twprojetos.api.controles;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.twprojetos.api.dto.CargoDTO;
import br.com.treinaweb.twprojetos.entidades.Cargo;
import br.com.treinaweb.twprojetos.servicos.CargoServico;

@RestController
@RequestMapping("/api/v1/cargos")
public class CargoControleApi {

    @Autowired
    private CargoServico cargoServico;
    
    @GetMapping
    public List<Cargo> buscarTodos() {
    	List<Cargo> cargos = cargoServico.buscarTodos();
		
    	cargos.forEach(cargo -> {
    		Long id = cargo.getId();
    		
    		Link selfLink = linkTo(methodOn(CargoControleApi.class).buscarPorId(id)).withSelfRel().withType("GET");
    		Link editarLink = linkTo(methodOn(CargoControleApi.class).atualizar(null, id)).withSelfRel().withType("PUT");
    		Link exclusaoLink = linkTo(methodOn(CargoControleApi.class).excluirPorId(id)).withSelfRel().withType("DELETE");
    		
    		cargo.add(selfLink, editarLink, exclusaoLink);
    	});
    	
    	
    	return cargos;

    }

    @GetMapping("/{id}")
	public Cargo buscarPorId(@PathVariable Long id) {
		return cargoServico.buscarPorId(id);
	}
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
	public Cargo cadastrar(@RequestBody @Valid CargoDTO cargoDTO) {
		return cargoServico.cadastrar(cargoDTO);
	}
    
    @PutMapping("/{id}")
	public Cargo atualizar(@RequestBody @Valid CargoDTO cargoDTO, @PathVariable Long id) {
		return cargoServico.atualizar(cargoDTO, id);
	}

    @DeleteMapping("/{id}")
	public ResponseEntity<?> excluirPorId(@PathVariable Long id) {
		cargoServico.excluirPorId(id);
		
		return ResponseEntity.noContent().build();
	}
    
    
    
}
