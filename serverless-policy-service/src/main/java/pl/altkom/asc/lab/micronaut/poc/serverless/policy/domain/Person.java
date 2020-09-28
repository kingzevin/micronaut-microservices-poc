package pl.altkom.asc.lab.micronaut.poc.serverless.policy.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Person {
    private String firstName;
    private String lastName;
    private String pesel;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
