package pl.altkom.asc.lab.micronaut.poc.serverless.policy.service.api.v1.commands.createoffer.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Introspected
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY, 
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ChoiceQuestionAnswer.class, name = "choice"),
    @JsonSubTypes.Type(value = TextQuestionAnswer.class, name = "text"),
    @JsonSubTypes.Type(value = NumericQuestionAnswer.class, name = "numeric"),
})
public abstract class QuestionAnswer<T> {
    private String questionCode;
    private T answer;
}
