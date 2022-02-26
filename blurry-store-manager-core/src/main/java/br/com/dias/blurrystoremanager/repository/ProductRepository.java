package br.com.dias.blurrystoremanager.repository;

import br.com.dias.blurrystoremanager.domain.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<Product, UUID> {

}
