package com.example.ordermanager.controller;

import com.example.ordermanager.dto.ItemDto;
import com.example.ordermanager.model.Order;
import com.example.ordermanager.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public ResponseEntity createNewOrder(@RequestBody List<ItemDto> itemDtos) {
        orderService.addOrder(itemDtos);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/last")
    public ResponseEntity<Order> getLatestOrder() {
        try {
            Order newestOrderOptional = orderService.getNewestOrder();
            return new ResponseEntity<>(newestOrderOptional, HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeOrder(@PathVariable("id") Long id) {
        orderService.removeOrderById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
