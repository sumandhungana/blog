package demo.treeleaf.ai.blog.usecase.add;

import demo.treeleaf.ai.blog.usecase.UseCase;

/**
 * @author suman dhungana
 */
public record AddBlogUseCaseRequest(
        String id,
        String name,
        String description
) implements UseCase.Request {
}
