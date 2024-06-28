package store.book.order.dtos;
import lombok.Data;
@Data
public class OrderItemDTO {
    private Long id;
    private Long orderId;
    private Long bookId;
    private int quantity;
    private double price;
}
