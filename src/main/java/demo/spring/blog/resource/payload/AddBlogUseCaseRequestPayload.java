package demo.spring.blog.resource.payload;

import io.soabase.recordbuilder.core.RecordBuilder;

/**
 * @author suman dhungana
 */
public record AddBlogUseCaseRequestPayload(
        String id,
        String name,
        String description
) {
}
