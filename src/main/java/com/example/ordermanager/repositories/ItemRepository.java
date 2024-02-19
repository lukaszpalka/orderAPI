package com.example.ordermanager.repositories;

import com.example.ordermanager.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT order_id FROM items WHERE id = :itemId", nativeQuery = true)
    Long findOrderIdByItemId(@Param("itemId") Long id);

}
