package pl.altkom.asc.lab.micronaut.poc.serverless.documents.infrastructure.adapters.web

import io.micronaut.http.annotation.Controller
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.validation.Validated
import pl.altkom.asc.lab.micronaut.poc.serverless.documents.api.DocumentsOperations
import pl.altkom.asc.lab.micronaut.poc.serverless.documents.api.queries.finddocuments.FindDocumentsResult
import pl.altkom.asc.lab.micronaut.poc.serverless.documents.api.queries.finddocuments.GeneratedDocument
import pl.altkom.asc.lab.micronaut.poc.serverless.documents.domain.PolicyDocumentRepository
import pl.altkom.asc.lab.micronaut.poc.serverless.documents.domain.ReportGenerator;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent
import pl.altkom.asc.lab.micronaut.poc.serverless.documents.domain.PolicyDocument

@ExecuteOn(TaskExecutors.IO)
@Validated
@Controller("/documents")
class DocumentsController(private val policyDocumentRepository: PolicyDocumentRepository, private val reportGenerator: ReportGenerator) : DocumentsOperations {

    override fun find(policyNumber: String): FindDocumentsResult {
        val findByPolicyNumber = policyDocumentRepository.findByPolicyNumber(policyNumber)
        val list = findByPolicyNumber.map { policyDocument -> GeneratedDocument(policyNumber, policyDocument.bytes) }
        return FindDocumentsResult(list)
    }

    override fun policyRegisteredNotify(event: PolicyRegisteredEvent){
        val generatedDocument = reportGenerator.generate(event)

        val policyDocument = PolicyDocument(
                id = null,
                policyNumber = event.policy.number,
                bytes = generatedDocument?.body()!!
        )

        policyDocumentRepository.add(policyDocument)
    }
}
