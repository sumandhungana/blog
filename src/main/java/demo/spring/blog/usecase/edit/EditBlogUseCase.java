package demo.spring.blog.usecase.edit;

import demo.spring.blog.repository.BlogRepository;
import demo.spring.blog.usecase.UseCase;
import demo.spring.blog.model.BlogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author suman dhungana
 */
@Service
@Transactional
public class EditBlogUseCase implements UseCase<EditBlogUseCaseRequest, EditBlogUseCaseResponse> {

    private final BlogRepository blogRepository;

    @Autowired
    public EditBlogUseCase(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Optional<EditBlogUseCaseResponse> execute(EditBlogUseCaseRequest request) {
        Optional<BlogEntity> blogData = blogRepository.findById(request.id());
        if (blogData.isEmpty()) {
            return Optional.empty();
        }
        BlogEntity _blog = blogData.get();
        _blog.setName(request.name());
        _blog.setDescription(request.description());
        var resp = blogRepository.save(_blog);
        return Optional.of(new EditBlogUseCaseResponse(resp.getId()));
    }
}
