package com.sgic.myleave.controller;

import com.sgic.myleave.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class UserController {

	List<User> users = new ArrayList<>();

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUser() {
		ResponseEntity<List<User>> response = new ResponseEntity<>(users, HttpStatus.OK);
		return response;
	}

	@PostMapping("/users")
	public HttpStatus createUsers(@RequestBody User user) {

		System.out.println("Created : "+user.getEmpId() + " , " + user.getName() + " , " + user.getDesignation());

		if ((user.getEmpId() != 0) && (user.getName() != null) && (user.getDesignation() != null)) {
			users.add(user);
			return HttpStatus.CREATED;
		} else {
			return HttpStatus.BAD_REQUEST;
		}
	}

	@PutMapping("/users/{empId}")
	public HttpStatus editUsers(@RequestBody User user, @PathVariable("empId") int id) {

		for (User existingUser : users) {
			if (existingUser.getEmpId() == id) {
				existingUser.setDesignation(user.getDesignation());
				existingUser.setName(user.getName());
				return HttpStatus.ACCEPTED;
			}
		}
		return HttpStatus.BAD_REQUEST;
	}

	@DeleteMapping("/users/{empId}")
	public HttpStatus deleteUsers(@PathVariable("empId") int id) {

		for (User existingUser : users) {
			if (existingUser.getEmpId() == id) {
				//users.remove(users.indexOf(existingUser));
				users.remove(existingUser);
				return HttpStatus.OK;
			}
		}
		return HttpStatus.BAD_REQUEST;
	}

}