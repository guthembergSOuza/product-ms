package com.desafio.productms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@ApiOperation("Rota responsável por retornar um Produto pelo seu id")
    @PutMapping("/{id}")
    public ResponseEntity<Product> atualizar(@PathVariable(required = true) Long id, @RequestBody @Valid ProductDTO produto) {
	
		return ResponseEntity.ok(this.productService.atualizar(id, produto));
    }
	
	@ApiOperation("Buscar Produto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> buscarProduto(@PathVariable(required = true) Long id) 
    {
		return ResponseEntity.ok(this.productService.buscarProduto(id));
    }
	
	@ApiOperation("Listar todos os Produtos")
    @GetMapping
    public List<Product> listar() 
    {
		return this.productService.listarProdutos();
    }
	
	@ApiOperation("Listar Produtos por Parametros")
    @GetMapping("/search")
    public List<Product> filtrarProdutos(@RequestParam(name="min_price",required = false) String min_price,
    		@RequestParam(name="max_price",required = false) String max_price) 
    {
		return this.productService.filtrar(min_price,max_price);
    }
	
	@ApiOperation("Recebe o ID da produto e o exclui do banco.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> exluir(@PathVariable(required = true) Long id) {
		this.productService.excluir(id);
		return ResponseEntity.noContent().build();
    }
	
}
