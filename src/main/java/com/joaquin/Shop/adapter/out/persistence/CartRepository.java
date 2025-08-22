package com.joaquin.Shop.adapter.out.persistence;

import com.joaquin.Shop.domain.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}
