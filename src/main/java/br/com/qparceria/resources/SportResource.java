package br.com.qparceria.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.qparceria.domain.Sport;
import br.com.qparceria.dto.SportDTO;
import br.com.qparceria.services.SportService;

@RestController
@RequestMapping(value="/sports")
public class SportResource {

	@Autowired
	private SportService sportService;

	@RequestMapping(value="/all", method=RequestMethod.GET)	
	public ResponseEntity<List<SportDTO>> findAll() {
		List<Sport> list = sportService.findAll();
		List<SportDTO> listDTO = list.stream().map(obj -> new SportDTO(obj.getId(), obj.getName())).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
