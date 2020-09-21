package pl.altkom.asc.lab.micronaut.poc.serverless.documents.api

import pl.altkom.asc.lab.micronaut.poc.serverless.documents.api.queries.finddocuments.FindDocumentsResult
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Body
import org.jetbrains.annotations.NotNull

interface DocumentsOperations {

    @Get("/{policyNumber}")
    fun find(@NotNull policyNumber: String): FindDocumentsResult

    @Post("/register")
    fun policyRegisteredNotify(@NotNull @Body event: PolicyRegisteredEvent)
}
