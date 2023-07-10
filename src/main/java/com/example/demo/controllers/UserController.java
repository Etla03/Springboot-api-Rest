package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.SignUpRequestDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.utils.WrapperResponse;

@RestController
public class UserController {
	
	
	
	public ResponseEntity<WrapperResponse<UserDTO>> signUp(@RequestBody SignUpRequestDTO user){
		
		
		
		return new WrapperResponse<UserDTO>(true,"Succes",dtoUser).createResponse();
		
	}
	

}
