package com.desafio.productms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.productms.domain.Product;
import com.desafio.productms.service.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("products")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
    private ProductService productService;
	
	@ApiOperation("Rota responsável por salvar um Produto.")
    @PostMapping
    public ResponseEntity<Product> inserir(@RequestBody @Valid ProductDTO prod) {
		//try {
			return ResponseEntity.ok( this.productService.inserir(prod));
		/*} catch (ConstraintViolationException ex) {
			
			ConstraintViolation<?> violation = ex.getConstraintViolations().iterator().next();
			String message = violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage();
              
			 throw ex;
			
		}*/
    }
	
	@ApiOperation("Rota responsável por atualizar um Produto")
    @PutMapping("/{id}")
    public ResponseEntity<Product> atualizar(@PathVariable(required = true) Long id, @RequestBody @Valid ProductDTO produto) {
	
		return ResponseEntity.ok(this.productService.atualizar(id, produto));
    }
	
}
