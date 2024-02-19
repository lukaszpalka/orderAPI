package com.example.ordermanager.service;

import com.example.ordermanager.dto.ItemDto;
import com.example.ordermanager.model.Item;
import com.example.ordermanager.model.Order;
import com.example.ordermanager.repositories.ItemRepository;
import com.example.ordermanager.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final ItemRepository itemRepo;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepo = orderRepository;
        this.itemRepo = itemRepository;
    }

    public void addOrder(List<ItemDto> itemDtos) {
        final Order order = new Order();
        order.setCreationDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        order.setModifyDate(order.getCreationDate());
        order.setItems(new ArrayList<>());
        for (ItemDto itemDto : itemDtos) {
            Item item = new Item();
            item.setPrice(itemDto.price());
            item.setName(itemDto.name());
            item.setAmount(itemDto.amount());
            order.getItems().add(item);
            itemRepo.save(item);
        }
        orderRepo.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Order getNewestOrder() {
        return orderRepo.findAllByOrderByCreationDateDesc().stream().findFirst().orElseThrow(() -> new NullPointerException("Not found"));
    }

    public Order getOrderById(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new NullPointerException("Not found"));
    }

    public void updateModifyDateByUpdatedItemId(Long id) {
        Order order = getOrderById(itemRepo.findOrderIdByItemId(id));
        order.setModifyDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        orderRepo.save(order);
    }

    public void removeOrderById(Long id) {
        orderRepo.deleteById(id);
    }

}
