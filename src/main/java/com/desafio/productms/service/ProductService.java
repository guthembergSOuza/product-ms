package com.desafio.productms.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
	
	public List<Product> filtrar(String min_price, String max_price, String q){
		
		List<Product> listaProdutos = this.repository.findAll((Specification<Product>) (root, cq, cb) -> {
            Predicate p = cb.conjunction();
            
            if(q !=null) {
            	p = cb.and(p, cb.equal(root.get("name"), cb.literal(q)));
            	p = cb.or(p, cb.equal(root.get("description"), cb.literal(q)));
            	
            }
		    if (max_price != null) {
		    	p = cb.and(p, cb.lessThanOrEqualTo(root.get("price"), cb.literal(Double.parseDouble(max_price))));
		    }
		    if (min_price != null) {
		    	p = cb.and(p, cb.greaterThanOrEqualTo(root.get("price"), cb.literal(Double.parseDouble(min_price))));
		    }
		    
		    return p;
        
		});

		return listaProdutos;
	}
	
	@Transactional
    public int excluir(Long id) {
		
		if(id != null && id > 0) {
			
			Optional<Product> produto = this.repository.findById(id);
			if(produto.isPresent()) {
				repository.deleteById(id);
				return 0;
			}
			
		}
		
		return 1;
    }
}
