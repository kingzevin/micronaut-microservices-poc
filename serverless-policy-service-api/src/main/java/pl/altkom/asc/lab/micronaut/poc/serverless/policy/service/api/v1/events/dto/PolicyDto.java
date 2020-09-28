package pl.altkom.asc.lab.micronaut.poc.serverless.policy.service.api.v1.events.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Introspected
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDto {
    private String number;
    private LocalDate from;
    private LocalDate to;
    private String policyHolder;
    private String productCode;
    private BigDecimal totalPremium;
    private String agentLogin;
    
    public String getNumber() {
        return number;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public String getPolicyHolder() {
        return policyHolder;
    }

    public String getProductCode() {
        return productCode;
    }

    public BigDecimal getTotalPremium() {
        return totalPremium;
    }

    public String getAgentLogin() {
        return agentLogin;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public void setPolicyHolder(String policyHolder) {
        this.policyHolder = policyHolder;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setTotalPremium(BigDecimal totalPremium) {
        this.totalPremium = totalPremium;
    }

    public void setAgentLogin(String agentLogin) {
        this.agentLogin = agentLogin;
    }

    @Override
    public String toString() {
        return "{" +
                "number: '" + number + '\'' +
                ", from: " + from +
                ", to: " + to +
                ", policyHolder: '" + policyHolder + '\'' +
                ", productCode: '" + productCode + '\'' +
                ", totalPremium: " + totalPremium +
                ", agentLogin: '" + agentLogin + '\'' +
                '}';
    }
}
