package com.example.ordermanager.controller;

import com.example.ordermanager.dto.ItemDto;
import com.example.ordermanager.service.ItemService;
import com.example.ordermanager.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/item", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ItemController {
    private final ItemService itemService;
    private final OrderService orderService;

    public ItemController(ItemService itemService, OrderService orderService) {
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateItemInOrder(@PathVariable("id") Long id,
                                            @RequestParam(name = "name", required = false) String name,
                                            @RequestParam(name = "price", required = false) Double price,
                                            @RequestParam(name = "amount", required = false) Integer amount) {
        try {
            itemService.updateItemById(id, new ItemDto(name, price, amount));
            orderService.updateModifyDateByUpdatedItemId(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
