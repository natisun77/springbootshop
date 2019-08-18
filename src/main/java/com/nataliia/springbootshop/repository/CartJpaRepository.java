package com.nataliia.springbootshop.repository;

import com.nataliia.springbootshop.model.Cart;
import com.nataliia.springbootshop.model.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartJpaRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT g FROM Cart c JOIN c.goodsInCart g WHERE c.id = :cartId")
    List<Good> loadAllGoodsFromCart(@Param("cartId") long cartId);
}
