all: raw

org:
	mvn clean install -DskipTests
	wsk -i action update /guest/poc/serverless-agent-portal-gateway target/original-serverless-agent-portal-gateway-1.0.jar --main pl.altkom.asc.lab.micronaut.poc.serverless.gateway.AgentPortalGatewayApplication --kind java:8 --web raw -m 512

raw:
	mvn clean install -DskipTests
	wsk -i action update /guest/poc/serverless-agent-portal-gateway target/serverless-agent-portal-gateway-1.0.jar --main pl.altkom.asc.lab.micronaut.poc.serverless.gateway.AgentPortalGatewayApplication --kind java:8 --web raw -m 512