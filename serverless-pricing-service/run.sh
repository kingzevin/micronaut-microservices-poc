curl -k https://172.17.0.1/api/v1/web/guest/poc/serverless-pricing-service/pricing/calculate -X POST -H "Content-Type: application/json" -d '{"productCode":"CAR","policyFrom":"2020-09-25","policyTo":"2020-09-29","selectedCovers":["C1"],"answers":[{"questionCode":"NUM_OF_CLAIM","type":"numeric","answer":"1"}]}'
