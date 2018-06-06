package br.com.qparceria.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.qparceria.services.ActivityService;

@RestController
@RequestMapping(value="/matches")
public class MatchResource {
	
	@Autowired
	private ActivityService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public ResponseEntity<Void> match(@PathVariable Integer id){
		service.match(id);
		return ResponseEntity.noContent().build();
	}
		
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)	
	public ResponseEntity<Void> deleteMatch(@PathVariable Integer id) {
		service.deleteMatch(id);
		return ResponseEntity.noContent().build();
	}	
}
