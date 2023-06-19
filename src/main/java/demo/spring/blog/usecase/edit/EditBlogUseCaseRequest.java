package demo.spring.blog.usecase.edit;

import demo.spring.blog.usecase.UseCase;

/**
 * @author suman dhungana
 */
public record EditBlogUseCaseRequest(
        long id
        , String name,
        String description) implements UseCase.Request {
}
