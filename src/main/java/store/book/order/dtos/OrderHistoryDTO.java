package store.book.order.dtos;

import java.util.Set;
import lombok.Data;
@Data
public class OrderHistoryDTO {
    private Long id;
    private Long userId;
    private Set<Long> orderIds;
}
