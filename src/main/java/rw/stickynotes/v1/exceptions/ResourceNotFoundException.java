package rw.stickynotes.v1.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String resourceName;

    private String field;

    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s with %s ['%s'] not found", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.field = fieldName;
        this.fieldValue = fieldValue;
    }
}
