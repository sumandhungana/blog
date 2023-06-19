package demo.spring.blog.resource;

import demo.spring.blog.converter.BlogConverter;
import demo.spring.blog.resource.payload.AddBlogUseCaseRequestPayload;
import demo.spring.blog.resource.payload.GetBlogRequestPayload;
import demo.spring.blog.usecase.add.AddBlogUseCase;
import demo.spring.blog.usecase.add.AddBlogUseCaseResponse;
import demo.spring.blog.usecase.delete.DeleteBlogUseCase;
import demo.spring.blog.usecase.delete.DeleteBlogUseCaseRequest;
import demo.spring.blog.usecase.edit.EditBlogUseCase;
import demo.spring.blog.usecase.edit.EditBlogUseCaseResponse;
import demo.spring.blog.usecase.search.GetAllBlogUseCase;
import demo.spring.blog.usecase.search.GetBlogUseCaseResponse;
import demo.spring.blog.resource.payload.EditBlogRequestPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * @author suman dhungana
 */

@RestController
@RequestMapping("/api")
@Slf4j
public class BlogResource {

    private final AddBlogUseCase addBlogUseCase;
    private final EditBlogUseCase editBlogUseCase;
    private final GetAllBlogUseCase getAllBlogUseCase;
    private final DeleteBlogUseCase deleteBlogUseCase;
    Logger logger = Logger.getLogger(BlogResource.class.getName());


    @Autowired
    public BlogResource(AddBlogUseCase addBlogUseCase,
                        EditBlogUseCase editBlogUseCase,
                        GetAllBlogUseCase getAllBlogUseCase,
                        DeleteBlogUseCase deleteBlogUseCase) {
        this.addBlogUseCase = addBlogUseCase;
        this.editBlogUseCase = editBlogUseCase;
        this.getAllBlogUseCase = getAllBlogUseCase;
        this.deleteBlogUseCase = deleteBlogUseCase;
    }

    @PostMapping("/blogs")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('MODERATOR') or hasRole('ROLEADMIN')")
    public ResponseEntity<Optional<AddBlogUseCaseResponse>> createBlogs(@RequestBody AddBlogUseCaseRequestPayload payload) {
        logger.info("Inside here");
        var request = BlogConverter.toRequest(payload);
        var response = this.addBlogUseCase.execute(request);
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/blogs")
    public ResponseEntity<Optional<GetBlogUseCaseResponse>> getBlogs(@RequestParam(required = false) String name) {
        try {
            var payload = new GetBlogRequestPayload(name);
            var response = this.getAllBlogUseCase.execute(BlogConverter.toRequest(payload));

            if (response.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/blogs/{id}")
    public ResponseEntity<EditBlogUseCaseResponse> updateBlog(@PathVariable("id") long id, @RequestBody EditBlogRequestPayload payload) {
        var response = this.editBlogUseCase.execute(BlogConverter.toRequest(payload, id));

        return response.map(editBlogUseCaseResponse ->
                new ResponseEntity<>(editBlogUseCaseResponse, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("/blogs")
    public ResponseEntity<HttpStatus> deleteAllBlogs() {
        try {
            deleteBlogUseCase.execute(new DeleteBlogUseCaseRequest());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
