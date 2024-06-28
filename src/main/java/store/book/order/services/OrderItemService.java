package store.book.order.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.book.books.repositories.BookRepository;
import store.book.order.dtos.OrderItemDTO;
import store.book.order.entities.OrderEntity;
import store.book.order.entities.OrderItemEntity;
import store.book.order.repositories.OrderItemRepository;
import store.book.order.repositories.OrderRepository;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrder(orderRepository.findById(orderItemDTO.getOrderId()).orElse(null));
        orderItemEntity.setBook(bookRepository.findById(orderItemDTO.getBookId()).orElse(null));
        orderItemEntity.setQuantity(orderItemDTO.getQuantity());
        orderItemEntity.setPrice(orderItemDTO.getPrice());

        orderItemEntity = orderItemRepository.save(orderItemEntity);

        return convertToDTO(orderItemEntity);
    }

    public OrderItemDTO getOrderItem(Long id) {
        return orderItemRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO) {
        return orderItemRepository.findById(id).map(orderItem -> {
            orderItem.setOrder(orderRepository.findById(orderItemDTO.getOrderId()).orElse(null));
            orderItem.setBook(bookRepository.findById(orderItemDTO.getBookId()).orElse(null));
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItem.setPrice(orderItemDTO.getPrice());

            orderItem = orderItemRepository.save(orderItem);
            return convertToDTO(orderItem);
        }).orElse(null);
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    private OrderItemDTO convertToDTO(OrderItemEntity orderItemEntity) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItemEntity.getId());
        orderItemDTO.setOrderId(orderItemEntity.getOrder().getId());
        orderItemDTO.setBookId(orderItemEntity.getBook().getId());
        orderItemDTO.setQuantity(orderItemEntity.getQuantity());
        orderItemDTO.setPrice(orderItemEntity.getPrice());

        return orderItemDTO;
    }
}
