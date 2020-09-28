package pl.altkom.asc.lab.micronaut.poc.serverless.policy.shared.specification;


import pl.altkom.asc.lab.micronaut.poc.serverless.policy.shared.exceptions.BusinessException;

public class SpecificationNotSatisfiedException extends BusinessException {
    public SpecificationNotSatisfiedException(String errorCode,Object[] params){
        super(errorCode, params);
    }
}
