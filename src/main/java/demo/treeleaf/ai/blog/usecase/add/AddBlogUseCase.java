package demo.treeleaf.ai.blog.usecase.add;

import demo.treeleaf.ai.blog.converter.BlogConverter;
import demo.treeleaf.ai.blog.repository.BlogRepository;
import demo.treeleaf.ai.blog.usecase.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author suman dhungana
 */
@Service
@Transactional
public class AddBlogUseCase implements UseCase<AddBlogUseCaseRequest, AddBlogUseCaseResponse> {

    private final BlogRepository blogRepository;

    @Autowired
    public AddBlogUseCase(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Optional<AddBlogUseCaseResponse> execute(AddBlogUseCaseRequest request) {
        var entity = BlogConverter.toEntity(request);
        var entityResponse = this.blogRepository.save(entity);
        return Optional.of(new AddBlogUseCaseResponse(entityResponse.getId()));
    }
}
