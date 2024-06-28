package store.book.order.dtos;

import java.util.Date;
import java.util.Set;
import lombok.Data;
@Data
public class OrderDTO {
    private Long id;
    private Long orderHistoryId;
    private Date orderDate;
    private String status;
    private Set<Long> orderItemIds;
}
