package br.com.qparceria.resources;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.qparceria.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@RequestMapping(method=RequestMethod.GET)	
	public ArrayList<User> listar() {
		User user1 = new User(1, "Gustavo Santos", "gustavo", "gustavosci@Live.com", "123456");
		User user2 = new User(2, "Daniela Morais", "dani", "danielamorais@Live.com", "789");
		
		ArrayList<User> list = new ArrayList<User>();
		list.add(user1);
		list.add(user2);
		
		return list;
	}
	
}
