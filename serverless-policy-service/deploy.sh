#!/bin/bash
wsk -i action update /guest/poc/serverless-policy-service target/serverless-policy-service-1.0.jar --main pl.altkom.asc.lab.micronaut.poc.serverless.policy.PolicyApplication --kind java:8 --web raw -m 512
