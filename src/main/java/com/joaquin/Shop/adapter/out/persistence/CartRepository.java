package com.joaquin.Shop.adapter.out.persistence;

import com.joaquin.Shop.domain.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    List<Cart> findByUserId(UUID userId);
}