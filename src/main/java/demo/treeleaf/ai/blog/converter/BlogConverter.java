package demo.treeleaf.ai.blog.converter;

import demo.treeleaf.ai.blog.model.BlogEntity;
import demo.treeleaf.ai.blog.resource.payload.AddBlogUseCaseRequestPayload;
import demo.treeleaf.ai.blog.resource.payload.EditBlogRequestPayload;
import demo.treeleaf.ai.blog.resource.payload.GetBlogRequestPayload;
import demo.treeleaf.ai.blog.usecase.add.AddBlogUseCaseRequest;
import demo.treeleaf.ai.blog.usecase.edit.EditBlogUseCaseRequest;
import demo.treeleaf.ai.blog.usecase.search.GetBlogUseCaseRequest;

/**
 * @author suman dhungana
 */
public class BlogConverter {


    public static AddBlogUseCaseRequest toRequest(AddBlogUseCaseRequestPayload payload) {
        return new AddBlogUseCaseRequest(payload.id(),
                payload.name(),
                payload.description()
        );

    }

    public static BlogEntity toEntity(AddBlogUseCaseRequest request) {
        BlogEntity entity = new BlogEntity();
        entity.setName(request.name());
        entity.setDescription(request.description());
        return entity;

    }

    public static GetBlogUseCaseRequest toRequest(GetBlogRequestPayload payload) {
        return new GetBlogUseCaseRequest(payload.name());
    }

    public static EditBlogUseCaseRequest toRequest(EditBlogRequestPayload payload, long id) {
        return new EditBlogUseCaseRequest(id,payload.name(), payload.description());
    }
}
