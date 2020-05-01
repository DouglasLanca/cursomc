package com.dlanca.cursomc.repositories;

import com.dlanca.cursomc.domain.RequestedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestedProductRepository extends JpaRepository<RequestedProduct, Integer> {

}
