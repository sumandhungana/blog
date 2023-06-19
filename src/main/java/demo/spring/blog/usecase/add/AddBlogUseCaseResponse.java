package demo.spring.blog.usecase.add;

import demo.spring.blog.usecase.UseCase;

/**
 * @author suman dhungana
 */
public record AddBlogUseCaseResponse(long id) implements UseCase.Response {
}
