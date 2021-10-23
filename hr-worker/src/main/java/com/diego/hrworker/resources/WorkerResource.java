package com.diego.hrworker.resources;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.hrworker.entities.Worker;
import com.diego.hrworker.services.WorkerService;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {
	
	private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);
	
	@Autowired
	private Environment env;

	@Autowired
	private WorkerService service;

	@GetMapping
	public ResponseEntity<List<Worker>> findAll() {
		List<Worker> list = service.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id) {
		/*
		 * try { Thread.sleep(3000L); } catch (Exception e) { // TODO: handle exception
		 * e.printStackTrace(); }
		 */
		
		logger.info("PORT = " + env.getProperty("local.server.port"));
		
		Optional<Worker> worker = service.findById(id);
		return worker.isPresent() ? ResponseEntity.ok(worker.get()) : ResponseEntity.notFound().build();
	}
}
