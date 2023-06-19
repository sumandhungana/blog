package demo.spring.blog.usecase.search;

import demo.spring.blog.usecase.UseCase;

/**
 * @author suman dhungana
 */
public record GetBlogUseCaseRequest(String name) implements UseCase.Request {
}
