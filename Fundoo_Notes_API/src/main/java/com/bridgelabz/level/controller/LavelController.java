package com.bridgelabz.level.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.level.dto.LabelDto;
import com.bridgelabz.level.model.Label;
import com.bridgelabz.level.service.IlabelService;
import com.bridgelabz.response.Response;

@RestController
@RequestMapping("/lavelservice")
public class LavelController {

	@Autowired(required = true)
	IlabelService lavelService;

	@PostMapping("/create")
	public ResponseEntity<Response> create(@RequestBody LabelDto levelDto, @RequestHeader String tocken) {
		Response response = lavelService.createLabel(levelDto, tocken);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Response> delete(@RequestHeader String tocken, @RequestParam String lavelId) {
		Response response = lavelService.deleteLabel(tocken, lavelId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<Response> update(@RequestBody LabelDto lavelDto, @RequestHeader String tocken,
			@RequestParam String lavelId) {
		Response response = lavelService.updateLabel(lavelDto, tocken, lavelId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public List<Label> listLavel(@RequestHeader String tocken) {
		return lavelService.getAllLabel(tocken);

	}

}
