package store.book.order.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.book.order.dtos.OrderHistoryDTO;
import store.book.order.services.OrderHistoryService;

@RestController
@RequestMapping("/order-histories")
public class OrderHistoryController {
    @Autowired
    private OrderHistoryService orderHistoryService;

    @PostMapping
    public ResponseEntity<OrderHistoryDTO> createOrderHistory(@RequestBody OrderHistoryDTO orderHistoryDTO) {
        return ResponseEntity.ok(orderHistoryService.createOrderHistory(orderHistoryDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderHistoryDTO> getOrderHistory(@PathVariable Long id) {
        return ResponseEntity.ok(orderHistoryService.getOrderHistory(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderHistoryDTO> updateOrderHistory(@PathVariable Long id, @RequestBody OrderHistoryDTO orderHistoryDTO) {
        return ResponseEntity.ok(orderHistoryService.updateOrderHistory(id, orderHistoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderHistory(@PathVariable Long id) {
        orderHistoryService.deleteOrderHistory(id);
        return ResponseEntity.noContent().build();
    }
}
