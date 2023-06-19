package demo.treeleaf.ai.blog.resource.payload;

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
