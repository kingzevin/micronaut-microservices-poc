all: raw

raw:
	mvn clean install -DskipTests
	wsk -i action update /guest/poc/serverless-dashboard-service target/serverless-dashboard-service-1.0.jar --main pl.altkom.asc.lab.micronaut.poc.serverless.dashboard.service.DashboardApplication --kind java:8 --web raw -m 512