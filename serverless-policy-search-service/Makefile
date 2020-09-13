all: raw

raw:
	mvn clean install
	wsk -i action update /guest/poc/serverless-policy-search-service target/serverless-policy-search-service-1.0.jar --main pl.altkom.asc.lab.micronaut.poc.serverless.policy.search.PolicySearchApplication --kind java:8 --web raw -m 512

