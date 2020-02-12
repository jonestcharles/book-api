package cscie57a.assgn11.books.controller;

import cscie57a.assgn11.books.exception.ResourceNotFoundException;
import cscie57a.assgn11.books.model.Review;
import cscie57a.assgn11.books.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API Interface
 */
@RestController
@RequestMapping("/books/{bookId}/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    /**
     * GET for a review using bookId and reviewId (seems redundant)
     * @param reviewId
     * @return ResponseEntity with Review and status
     */
    @GetMapping(value = "/{reviewId}")
    public ResponseEntity<?> getReviewByBook(@PathVariable final Long reviewId) {
        return new ResponseEntity<>(reviewService.findReviewById(reviewId), HttpStatus.OK);
    }

    /**
     * DELETE a review using reviewId
     * @param reviewId
     * @return
     */
    @DeleteMapping(value = "/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable final Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
