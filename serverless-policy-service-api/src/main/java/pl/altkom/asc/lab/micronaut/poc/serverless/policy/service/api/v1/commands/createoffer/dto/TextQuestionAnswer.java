package pl.altkom.asc.lab.micronaut.poc.serverless.policy.service.api.v1.commands.createoffer.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class TextQuestionAnswer extends QuestionAnswer<String> {
    @JsonCreator
    public TextQuestionAnswer(@JsonProperty("questionCode") String questionCode, @JsonProperty("answer") String answer) {
        super(questionCode, answer);
    }
}
