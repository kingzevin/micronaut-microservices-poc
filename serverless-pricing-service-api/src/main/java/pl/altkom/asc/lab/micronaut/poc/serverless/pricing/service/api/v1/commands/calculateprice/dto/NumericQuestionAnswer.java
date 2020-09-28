package pl.altkom.asc.lab.micronaut.poc.serverless.pricing.service.api.v1.commands.calculateprice.dto;

import java.math.BigDecimal;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class NumericQuestionAnswer extends QuestionAnswer<BigDecimal> {
    public NumericQuestionAnswer(String questionCode, BigDecimal answer) {
        super(questionCode, answer);
    }
}

