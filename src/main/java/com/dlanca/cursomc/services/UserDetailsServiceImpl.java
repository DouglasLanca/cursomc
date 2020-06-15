package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.Customer;
import com.dlanca.cursomc.repositories.CustomerRepository;
import com.dlanca.cursomc.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null){
            throw new UsernameNotFoundException(email);
        }

        return new UserSpringSecurity(customer.getId(), customer.getEmail(), customer.getPassword(), customer.getRoles());
    }
}
