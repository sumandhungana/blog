package demo.spring.blog.usecase.delete;

import demo.spring.blog.repository.BlogRepository;
import demo.spring.blog.usecase.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author suman dhungana
 */

@Service
@Transactional
public class DeleteBlogUseCase implements UseCase<DeleteBlogUseCaseRequest, DeleteBlogUseCaseResponse> {
    private final BlogRepository blogRepository;

    @Autowired
    public DeleteBlogUseCase(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Optional<DeleteBlogUseCaseResponse> execute(DeleteBlogUseCaseRequest request) {
        blogRepository.deleteAll();
        return Optional.empty();
    }
}
