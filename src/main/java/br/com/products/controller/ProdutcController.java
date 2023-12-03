package br.com.products.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.products.dtos.ProductRecordDto;
import br.com.products.model.Product;
import br.com.products.repository.ProductRepository;
import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/product")
public class ProdutcController {
	
	@Autowired
	ProductRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> productsList = repository.findAll();
		if(!productsList.isEmpty()) {
			for(Product product : productsList) {
				Long id = product.getId();
				product.add(linkTo(methodOn(ProdutcController.class).getOneProduct(id)).withSelfRel());
			}
		}
		//Teste branch
		return ResponseEntity.status(HttpStatus.OK).body(productsList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") Long id){
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
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value="id") Long id, 
												@RequestBody @Valid ProductRecordDto productDto){
		Optional<Product> productO = repository.findById(id);
		if(productO.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
		var productModel = productO.get();
		BeanUtils.copyProperties(productDto, productModel);
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(productModel));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id){
		Optional<Product> productO = repository.findById(id);
		if(productO.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
		repository.delete(productO.get());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully.");
	}
	
	

}
