package demo.spring.blog.usecase;

import java.util.Optional;

/**
 * @author suman dhungana
 */
@FunctionalInterface
public interface UseCase<I extends UseCase.Request, O extends UseCase.Response> {
    interface Request{}
    interface Response{}

    Optional<O> execute(I request);
}
