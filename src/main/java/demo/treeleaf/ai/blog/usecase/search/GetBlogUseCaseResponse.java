package demo.treeleaf.ai.blog.usecase.search;

import demo.treeleaf.ai.blog.model.BlogEntity;
import demo.treeleaf.ai.blog.usecase.UseCase;

import java.util.List;

/**
 * @author suman dhungana
 */
public record GetBlogUseCaseResponse(
        List<BlogEntity> blogEntities) implements UseCase.Response {
}
