package br.com.qparceria.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.qparceria.domain.Activity;
import br.com.qparceria.dto.ActivityDTO;
import br.com.qparceria.dto.ActivitySimpleConsultDTO;
import br.com.qparceria.services.ActivityService;

@RestController
@RequestMapping(value="/activities")
public class ActivityResource {
	
	@Autowired
	private ActivityService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)	
	public ResponseEntity<ActivityDTO> find(@PathVariable Integer id) {
		Activity obj = service.find(id);
		return ResponseEntity.ok().body(new ActivityDTO(obj));
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ActivityDTO objDTO){
		Activity obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ActivityDTO objDTO, @PathVariable Integer id){
		Activity obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)	
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/my", method=RequestMethod.GET)	
	public ResponseEntity<List<ActivitySimpleConsultDTO>> findAll() {
		List<Activity> list = service.findAllOfUserLogged();
		List<ActivitySimpleConsultDTO> listDTO = list.stream().map(obj -> new ActivitySimpleConsultDTO(obj)).collect(Collectors.toList());						
		return ResponseEntity.ok().body(listDTO);
	}

}
