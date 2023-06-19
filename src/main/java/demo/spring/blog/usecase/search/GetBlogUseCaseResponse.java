package demo.spring.blog.usecase.search;

import demo.spring.blog.model.BlogEntity;
import demo.spring.blog.usecase.UseCase;

import java.util.List;

/**
 * @author suman dhungana
 */
public record GetBlogUseCaseResponse(
        List<BlogEntity> blogEntities) implements UseCase.Response {
}
