package demo.treeleaf.ai.blog.resource;

import demo.treeleaf.ai.blog.converter.BlogConverter;
import demo.treeleaf.ai.blog.resource.payload.AddBlogUseCaseRequestPayload;
import demo.treeleaf.ai.blog.resource.payload.EditBlogRequestPayload;
import demo.treeleaf.ai.blog.resource.payload.GetBlogRequestPayload;
import demo.treeleaf.ai.blog.usecase.add.AddBlogUseCase;
import demo.treeleaf.ai.blog.usecase.add.AddBlogUseCaseResponse;
import demo.treeleaf.ai.blog.usecase.delete.DeleteBlogUseCase;
import demo.treeleaf.ai.blog.usecase.delete.DeleteBlogUseCaseRequest;
import demo.treeleaf.ai.blog.usecase.edit.EditBlogUseCase;
import demo.treeleaf.ai.blog.usecase.edit.EditBlogUseCaseResponse;
import demo.treeleaf.ai.blog.usecase.search.GetAllBlogUseCase;
import demo.treeleaf.ai.blog.usecase.search.GetBlogUseCaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<Optional<AddBlogUseCaseResponse>> createBlogs(@RequestBody AddBlogUseCaseRequestPayload payload) {
        System.out.println("Inside here");
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
