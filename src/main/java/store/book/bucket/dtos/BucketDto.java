package store.book.bucket.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
public class BucketDto {
    private Long id;
    private Long userId;
    private Set<Long> bookIds;
}