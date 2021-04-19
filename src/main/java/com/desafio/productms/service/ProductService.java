package com.desafio.productms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.productms.controller.ProductDTO;
import com.desafio.productms.domain.Product;
import com.desafio.productms.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional
    public Product inserir(ProductDTO newProduto) {

		Product prod = Product.builder().name(newProduto.getName() ).description(newProduto.getDescription()).price(newProduto.getPrice())
		.build();

		return this.repository.save(prod);
    }
	
	@Transactional
	public Product atualizar(Long id, ProductDTO produtoDTO) {
		
		Optional<Product> produtoExistente = this.repository.findById(id);
		
		if(produtoExistente.isPresent()) {
			Product produto = produtoExistente.get();
			
			produto.setName(produtoDTO.getName());
			produto.setDescription(produtoDTO.getDescription());
			produto.setPrice(produtoDTO.getPrice());		
		
			return this.repository.save(produto);	
		}
		
		return null;
	}
	
	public Product buscarProduto(Long id) {
		
		Optional<Product> produtoExistente = this.repository.findById(id);
		
		return produtoExistente.isPresent() ? produtoExistente.get() : null;
	}
	
	public List<Product> listarProdutos(){
		return this.repository.findAll();
	}
}
