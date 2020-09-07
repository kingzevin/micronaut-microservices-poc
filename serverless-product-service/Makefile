all: raw

raw:
	mvn clean install
	$ wsk -i action update /guest/poc/serverless-product-service target/serverless-product-service-1.0.jar --main pl.altkom.asc.lab.micronaut.poc.serverless.product.service.ProductApplication --kind java:8 --web raw