package demo.spring.blog.usecase.add;

import demo.spring.blog.usecase.UseCase;

/**
 * @author suman dhungana
 */
public record AddBlogUseCaseRequest(
        String id,
        String name,
        String description
) implements UseCase.Request {
}
