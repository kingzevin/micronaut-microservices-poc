package pl.altkom.asc.lab.micronaut.poc.serverless.policy.commands;

import pl.altkom.asc.lab.micronaut.poc.serverless.command.bus.CommandHandler;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.domain.AgentRef;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.domain.Offer;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.domain.OfferRepository;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.domain.Person;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.domain.PolicyFactory;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.infrastructure.adapters.mhttp.EventPublisher;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.service.api.v1.events.PolicyRegisteredEvent;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.service.api.v1.events.dto.PolicyDto;

import java.time.LocalDate;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class CreatePolicyHandler implements CommandHandler<CreatePolicyResult, CreatePolicyCommand> {

    private final PolicyRepository policyRepository;
    private final OfferRepository offerRepository;
    private final PolicyFactory policyFactory = new PolicyFactory();
    private final EventPublisher eventPublisher = new EventPublisher();

    @Transactional
    @Override
    public CreatePolicyResult handle(CreatePolicyCommand cmd) {
        //get offer
        System.out.println("dashabi");
        System.out.println(cmd.getOfferNumber());
        Offer offer = offerRepository.getByNumber(cmd.getOfferNumber());

        //if offer not expired and not already converted
        if (offer.isExpired(LocalDate.now())) {
            throw new RuntimeException("Offer has expired");
        }

        //create policy from offer
        Person policyHolder = new Person(cmd.getPolicyHolder().getFirstName(), cmd.getPolicyHolder().getLastName(), cmd.getPolicyHolder().getTaxId());
        AgentRef agent = AgentRef.of(cmd.getAgentLogin());
        Policy policy = policyFactory.fromOffer(offer, policyHolder, agent);

        //save policy and update offer
        policyRepository.save(policy);

        //publish events
        eventPublisher.policyRegisteredEvent(policy.getNumber(), createEvent(policy));

        return new CreatePolicyResult(policy.getNumber());
    }

    private PolicyRegisteredEvent createEvent(Policy policy) {
        return new PolicyRegisteredEvent(
                new PolicyDto(
                        policy.getNumber(),
                        policy.versions().lastVersion().getVersionValidityPeriod().getFrom(),
                        policy.versions().lastVersion().getVersionValidityPeriod().getTo(),
                        policy.versions().lastVersion().getPolicyHolder().getFullName(),
                        policy.versions().lastVersion().getProductCode(),
                        policy.versions().lastVersion().getTotalPremiumAmount(),
                        null
                )
        );
    }
}
