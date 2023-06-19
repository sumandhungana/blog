package demo.treeleaf.ai.blog.usecase.search;

import demo.treeleaf.ai.blog.usecase.UseCase;

/**
 * @author suman dhungana
 */
public record GetBlogUseCaseRequest(String name) implements UseCase.Request {
}
