package pl.altkom.asc.lab.micronaut.poc.serverless.gateway.client.v1;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import pl.altkom.asc.lab.micronaut.poc.documents.api.DocumentsOperations;
import pl.altkom.asc.lab.micronaut.poc.documents.api.queries.finddocuments.FindDocumentsResult;

@Client(id = "documents-service")
@Retryable(attempts = "2", delay = "2s")
public interface DocumentsGatewayClient extends DocumentsOperations {

    @Override
    FindDocumentsResult find(String policyNumber);
}
