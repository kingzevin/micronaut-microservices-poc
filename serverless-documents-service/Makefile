all: raw

org:
	mvn clean install -DskipTests
	wsk -i action update /guest/poc/serverless-documents-service target/original-serverless-documents-service-1.0.jar --main pl.altkom.asc.lab.micronaut.poc.serverless.documents.DocumentsApplicationJava --docker kingzevin/java8action_documents:latest --web raw -m 512

raw:
	mvn clean install -DskipTests
	wsk -i action update /guest/poc/serverless-documents-service target/serverless-documents-service-1.0.jar --main pl.altkom.asc.lab.micronaut.poc.serverless.documents.DocumentsApplicationJava --docker kingzevin/java8action_documents:latest --web raw -m 512