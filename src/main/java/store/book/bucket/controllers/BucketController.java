package store.book.bucket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.book.bucket.dtos.BucketDto;
import store.book.bucket.services.BucketService;

@RestController
@RequestMapping("/buckets")
public class BucketController {
    @Autowired
    private BucketService bucketService;

    @PostMapping
    public ResponseEntity<BucketDto> createBucket(@RequestBody BucketDto bucketDTO) {
        return ResponseEntity.ok(bucketService.createBucket(bucketDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BucketDto> getBucket(@PathVariable Long id) {
        return ResponseEntity.ok(bucketService.getBucket(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BucketDto> updateBucket(@PathVariable Long id, @RequestBody BucketDto bucketDTO) {
        return ResponseEntity.ok(bucketService.updateBucket(id, bucketDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBucket(@PathVariable Long id) {
        bucketService.deleteBucket(id);
        return ResponseEntity.noContent().build();
    }
}