package br.com.qparceria.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.qparceria.domain.Mate;
import br.com.qparceria.dto.MatchDTO;
import br.com.qparceria.services.MatchService;

@RestController
@RequestMapping(value="/matches")
public class MatchResource {
	
	@Autowired
	private MatchService matchService;

	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public ResponseEntity<Void> match(@PathVariable Integer id){
		matchService.insert(id);
		return ResponseEntity.noContent().build();
	}
		
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)	
	public ResponseEntity<Void> deleteMatch(@PathVariable Integer id) {
		matchService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/produced", method=RequestMethod.GET)	
	public ResponseEntity<List<MatchDTO>> findAllProducedOfUserLogged() {
		List<Mate> list = matchService.findAllProducedOfUserLogged();
		List<MatchDTO> listDTO = list.stream().map(obj -> new MatchDTO(obj)).collect(Collectors.toList());						
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value="/received", method=RequestMethod.GET)	
	public ResponseEntity<List<MatchDTO>> findAllReceivedOfUserLogged() {
		List<Mate> list = matchService.findAllReceivedOfUserLogged();
		List<MatchDTO> listDTO = list.stream().map(obj -> new MatchDTO(obj)).collect(Collectors.toList());						
		return ResponseEntity.ok().body(listDTO);
	}

}
