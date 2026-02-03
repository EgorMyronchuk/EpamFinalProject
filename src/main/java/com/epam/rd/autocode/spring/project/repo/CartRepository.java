package com.epam.rd.autocode.spring.project.repo;

import com.epam.rd.autocode.spring.project.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long id);
}
