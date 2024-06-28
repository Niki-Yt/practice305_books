package store.book.order.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.book.auth.repositories.UserRepository;
import store.book.order.dtos.OrderHistoryDTO;
import store.book.order.entities.OrderEntity;
import store.book.order.entities.OrderHistoryEntity;
import store.book.order.repositories.OrderHistoryRepository;
import store.book.order.repositories.OrderRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderHistoryService {
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public OrderHistoryDTO createOrderHistory(OrderHistoryDTO orderHistoryDTO) {
        OrderHistoryEntity orderHistoryEntity = new OrderHistoryEntity();
        orderHistoryEntity.setUser(userRepository.findById(orderHistoryDTO.getUserId()).orElse(null));

        Set<OrderEntity> orderEntities = new HashSet<>();
        for (Long orderId : orderHistoryDTO.getOrderIds()) {
            orderEntities.add(orderRepository.findById(orderId).orElse(null));
        }
        orderHistoryEntity.setOrders(orderEntities);

        orderHistoryEntity = orderHistoryRepository.save(orderHistoryEntity);

        return convertToDTO(orderHistoryEntity);
    }

    public OrderHistoryDTO getOrderHistory(Long id) {
        return orderHistoryRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public OrderHistoryDTO updateOrderHistory(Long id, OrderHistoryDTO orderHistoryDTO) {
        return orderHistoryRepository.findById(id).map(orderHistory -> {
            orderHistory.setUser(userRepository.findById(orderHistoryDTO.getUserId()).orElse(null));

            Set<OrderEntity> orderEntities = new HashSet<>();
            for (Long orderId : orderHistoryDTO.getOrderIds()) {
                orderEntities.add(orderRepository.findById(orderId).orElse(null));
            }
            orderHistory.setOrders(orderEntities);

            orderHistory = orderHistoryRepository.save(orderHistory);
            return convertToDTO(orderHistory);
        }).orElse(null);
    }

    public void deleteOrderHistory(Long id) {
        orderHistoryRepository.deleteById(id);
    }

    private OrderHistoryDTO convertToDTO(OrderHistoryEntity orderHistoryEntity) {
        OrderHistoryDTO orderHistoryDTO = new OrderHistoryDTO();
        orderHistoryDTO.setId(orderHistoryEntity.getId());
        orderHistoryDTO.setUserId(orderHistoryEntity.getUser().getId());

        Set<Long> orderIds = new HashSet<>();
        for (OrderEntity order : orderHistoryEntity.getOrders()) {
            orderIds.add(order.getId());
        }
        orderHistoryDTO.setOrderIds(orderIds);

        return orderHistoryDTO;
    }
}
