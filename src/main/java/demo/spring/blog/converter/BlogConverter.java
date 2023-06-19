package demo.spring.blog.converter;

import demo.spring.blog.model.BlogEntity;
import demo.spring.blog.resource.payload.AddBlogUseCaseRequestPayload;
import demo.spring.blog.usecase.add.AddBlogUseCaseRequest;
import demo.spring.blog.usecase.search.GetBlogUseCaseRequest;
import demo.spring.blog.resource.payload.EditBlogRequestPayload;
import demo.spring.blog.resource.payload.GetBlogRequestPayload;
import demo.spring.blog.usecase.edit.EditBlogUseCaseRequest;

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
