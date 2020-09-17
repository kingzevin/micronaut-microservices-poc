package pl.altkom.asc.lab.micronaut.poc.serverless.documents.domain

interface PolicyDocumentRepository {
    fun add(document: PolicyDocument)
    fun findByPolicyNumber(policyNumber: String): List<PolicyDocument>
}