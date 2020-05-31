package com.dlanca.cursomc.repositories;

import com.dlanca.cursomc.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Transactional(readOnly=true)
    Customer findByEmail(String email);
}
