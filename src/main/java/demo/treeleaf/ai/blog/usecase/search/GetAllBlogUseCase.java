package demo.treeleaf.ai.blog.usecase.search;

import demo.treeleaf.ai.blog.model.BlogEntity;
import demo.treeleaf.ai.blog.repository.BlogRepository;
import demo.treeleaf.ai.blog.usecase.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author suman dhungana
 */
@Service
@Transactional
public class GetAllBlogUseCase implements UseCase<GetBlogUseCaseRequest, GetBlogUseCaseResponse> {

    private final BlogRepository blogRepository;

    @Autowired
    public GetAllBlogUseCase(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Optional<GetBlogUseCaseResponse> execute(GetBlogUseCaseRequest request) {
        List<BlogEntity> blog = new ArrayList<>();
        if (request.name() == null) {
            var entityResponse = this.blogRepository.findAll();
            blog.addAll(entityResponse);
        }
        return Optional.of(new GetBlogUseCaseResponse(blog));

    }
}
