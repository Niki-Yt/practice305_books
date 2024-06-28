package store.book.order.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.book.order.dtos.OrderItemDTO;
import store.book.order.services.OrderItemService;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        return ResponseEntity.ok(orderItemService.createOrderItem(orderItemDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItem(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.getOrderItem(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemDTO orderItemDTO) {
        return ResponseEntity.ok(orderItemService.updateOrderItem(id, orderItemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
