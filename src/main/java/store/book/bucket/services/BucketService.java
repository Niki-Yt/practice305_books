package store.book.bucket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.book.auth.entities.UserEntity;

import store.book.auth.repositories.UserRepository;
import store.book.books.entities.BookEntity;

import store.book.books.repositories.BookRepository;
import store.book.bucket.dtos.BucketDto;
import store.book.bucket.entities.BucketEntity;
import store.book.bucket.repositories.BucketRepository;


import java.util.HashSet;
import java.util.Set;

@Service
public class BucketService {
    @Autowired
    private BucketRepository bucketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public BucketDto createBucket(BucketDto bucketDTO) {
        BucketEntity bucketEntity = new BucketEntity();
        bucketEntity.setUser(userRepository.findById(bucketDTO.getUserId()).orElse(null));

        Set<BookEntity> bookEntities = new HashSet<>();
        for (Long bookId : bucketDTO.getBookIds()) {
            bookEntities.add(bookRepository.findById(bookId).orElse(null));
        }
        bucketEntity.setBooks(bookEntities);

        bucketEntity = bucketRepository.save(bucketEntity);

        return convertToDTO(bucketEntity);
    }

    public BucketDto getBucket(Long id) {
        return bucketRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public BucketDto updateBucket(Long id, BucketDto bucketDTO) {
        return bucketRepository.findById(id).map(bucket -> {
            bucket.setUser(userRepository.findById(bucketDTO.getUserId()).orElse(null));

            Set<BookEntity> bookEntities = new HashSet<>();
            for (Long bookId : bucketDTO.getBookIds()) {
                bookEntities.add(bookRepository.findById(bookId).orElse(null));
            }
            bucket.setBooks(bookEntities);

            bucket = bucketRepository.save(bucket);
            return convertToDTO(bucket);
        }).orElse(null);
    }

    public void deleteBucket(Long id) {
        bucketRepository.deleteById(id);
    }

    private BucketDto convertToDTO(BucketEntity bucketEntity) {
        BucketDto bucketDTO = new BucketDto();
        bucketDTO.setId(bucketEntity.getId());
        bucketDTO.setUserId(bucketEntity.getUser().getId());

        Set<Long> bookIds = new HashSet<>();
        for (BookEntity book : bucketEntity.getBooks()) {
            bookIds.add(book.getId());
        }
        bucketDTO.setBookIds(bookIds);

        return bucketDTO;
    }
}
