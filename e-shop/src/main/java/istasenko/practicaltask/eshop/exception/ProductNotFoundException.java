package istasenko.practicaltask.eshop.exception;

import javax.persistence.NoResultException;

public class ProductNotFoundException extends NoResultException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
