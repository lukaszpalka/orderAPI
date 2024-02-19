package com.example.ordermanager.repositories;

import com.example.ordermanager.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o ORDER BY o.creationDate DESC")
    List<Order> findAllByOrderByCreationDateDesc();

}
