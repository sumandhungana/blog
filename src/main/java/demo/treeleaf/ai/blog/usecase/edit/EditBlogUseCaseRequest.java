package demo.treeleaf.ai.blog.usecase.edit;

import demo.treeleaf.ai.blog.usecase.UseCase;

/**
 * @author suman dhungana
 */
public record EditBlogUseCaseRequest(
        long id
        , String name,
        String description) implements UseCase.Request {
}
