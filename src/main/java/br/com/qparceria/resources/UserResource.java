package br.com.qparceria.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.qparceria.domain.User;
import br.com.qparceria.dto.UserDTO;
import br.com.qparceria.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)	
	public ResponseEntity<User> find(@PathVariable Integer id) {
		User obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value="/username", method=RequestMethod.GET)	
	public ResponseEntity<UserDTO> find(@RequestParam(value="value") String username) {
		UserDTO objDTO = service.findByUsername(username);		
		return ResponseEntity.ok().body(objDTO);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO objDTO){
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody UserDTO objDTO, @PathVariable Integer id){
		User obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)	
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)	
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());						
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value="/page", method=RequestMethod.GET)	
	public ResponseEntity<Page<UserDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<User> listPage = service.findPage(page, linesPerPage, orderBy, direction);
		Page<UserDTO> pageDTO = listPage.map(obj -> new UserDTO(obj));						
		return ResponseEntity.ok().body(pageDTO);
	}

	@RequestMapping(value="/pic", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadPic(@RequestParam(name="file") MultipartFile file){
		return ResponseEntity.created(service.uploadPicUser(file)).build();
	}

}
