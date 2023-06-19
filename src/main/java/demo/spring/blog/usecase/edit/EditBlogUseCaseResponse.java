package demo.spring.blog.usecase.edit;

import demo.spring.blog.usecase.UseCase;

/**
 * @author suman dhungana
 */
public record EditBlogUseCaseResponse(long id) implements UseCase.Response {
}
