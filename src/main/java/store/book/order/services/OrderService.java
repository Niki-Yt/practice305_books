package store.book.order.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.book.order.dtos.OrderDTO;
import store.book.order.entities.OrderEntity;
import store.book.order.entities.OrderHistoryEntity;
import store.book.order.entities.OrderItemEntity;
import store.book.order.repositories.OrderHistoryRepository;
import store.book.order.repositories.OrderItemRepository;
import store.book.order.repositories.OrderRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderDTO createOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderHistory(orderHistoryRepository.findById(orderDTO.getOrderHistoryId()).orElse(null));
        orderEntity.setOrderDate(new Date());
        orderEntity.setStatus(orderDTO.getStatus());

        Set<OrderItemEntity> orderItems = new HashSet<>();
        for (Long itemId : orderDTO.getOrderItemIds()) {
            orderItems.add(orderItemRepository.findById(itemId).orElse(null));
        }
        orderEntity.setOrderItems(orderItems);

        orderEntity = orderRepository.save(orderEntity);

        return convertToDTO(orderEntity);
    }

    public OrderDTO getOrder(Long id) {
        return orderRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        return orderRepository.findById(id).map(order -> {
            order.setOrderHistory(orderHistoryRepository.findById(orderDTO.getOrderHistoryId()).orElse(null));
            order.setOrderDate(orderDTO.getOrderDate());
            order.setStatus(orderDTO.getStatus());

            Set<OrderItemEntity> orderItems = new HashSet<>();
            for (Long itemId : orderDTO.getOrderItemIds()) {
                orderItems.add(orderItemRepository.findById(itemId).orElse(null));
            }
            order.setOrderItems(orderItems);

            order = orderRepository.save(order);
            return convertToDTO(order);
        }).orElse(null);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setOrderHistoryId(orderEntity.getOrderHistory().getId());
        orderDTO.setOrderDate(orderEntity.getOrderDate());
        orderDTO.setStatus(orderEntity.getStatus());

        Set<Long> orderItemIds = new HashSet<>();
        for (OrderItemEntity item : orderEntity.getOrderItems()) {
            orderItemIds.add(item.getId());
        }
        orderDTO.setOrderItemIds(orderItemIds);

        return orderDTO;
    }
}
