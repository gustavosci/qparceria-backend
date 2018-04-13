package br.com.qparceria.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.qparceria.domain.City;
import br.com.qparceria.domain.UF;
import br.com.qparceria.dto.CityDTO;
import br.com.qparceria.dto.UfDTO;
import br.com.qparceria.services.CityService;
import br.com.qparceria.services.UFService;

@RestController
@RequestMapping(value="/ufs")
public class UFResource {

	@Autowired
	private UFService ufService;

	@Autowired
	private CityService cityService;

	@RequestMapping(value="/all", method=RequestMethod.GET)	
	public ResponseEntity<List<UfDTO>> findAll() {
		List<UF> list = ufService.findAll();
		List<UfDTO> listDTO = list.stream().map(obj -> new UfDTO(obj.getId(), obj.getSigla())).collect(Collectors.toList());						
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value="/{ufId}/cities", method=RequestMethod.GET)	
	public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer ufId) {
		List<City> list = cityService.findByUF(ufId);
		List<CityDTO> listDTO = list.stream().map(obj -> new CityDTO(obj.getId(), obj.getName())).collect(Collectors.toList());						
		return ResponseEntity.ok().body(listDTO);
	}
}
