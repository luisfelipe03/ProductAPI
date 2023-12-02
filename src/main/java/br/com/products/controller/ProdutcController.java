package br.com.products.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.products.dtos.ProductRecordDto;
import br.com.products.model.Product;
import br.com.products.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProdutcController {
	
	@Autowired
	ProductRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
		Optional<Product> productO = repository.findById(id);
		if(productO.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
		return ResponseEntity.status(HttpStatus.OK).body(productO.get());
	}
	
	@PostMapping
	public ResponseEntity<Product> saveProduct(@RequestBody @Valid
			ProductRecordDto productRecordDto){
		var productModel = new Product();
		BeanUtils.copyProperties(productRecordDto, productModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(productModel));
	}

}
