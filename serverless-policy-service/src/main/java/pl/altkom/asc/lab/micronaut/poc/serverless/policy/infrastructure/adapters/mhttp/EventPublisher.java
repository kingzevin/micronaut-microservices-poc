package pl.altkom.asc.lab.micronaut.poc.serverless.policy.infrastructure.adapters.mhttp;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.service.api.v1.events.PolicyRegisteredEvent;
import pl.altkom.asc.lab.micronaut.poc.serverless.policy.service.api.v1.events.PolicyTerminatedEvent;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.alibaba.fastjson.JSON;

public class EventPublisher {

    public static void coreRequest(String request_url,String body){
        final String url = request_url;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpPost request = null;

        String bodyData = body;
                //JSON.toJSONString(event.policy);
        try {
            httpClient = HttpClients.createDefault();
            request = new HttpPost(url);
            request.setEntity(new StringEntity(bodyData));
            request.addHeader("content-type", "application/json");
            response = httpClient.execute(request);
        } catch (Exception e) {
            System.out.println("Exception went in request!");
        } finally {
            if (null != httpClient) {
								try{
                		httpClient.close();
								}catch (Exception e){
								}
            }
            if (null != response) {
								try{
                		response.close();
									}catch (Exception e){
									}
            }
        }
    }
    public static void policyRegisteredEvent(String policyNumber, PolicyRegisteredEvent event) {
        String SearchURL = "http://172.17.0.1:10001/api/v1/web/guest/poc/serverless-policy-search-service/policies/register";
        String DocumentsURL = "http://172.17.0.1:10001/api/v1/web/guest/poc/serverless-documents-service/documents/register";
  			      
        String DashboardURL = "http://172.17.0.1:10001/api/v1/web/guest/poc/serverless-dashboard-service/documents/register";
				String searchBody = JSON.toJSONString(event.policy);
        String documentsBody = JSON.toJSONString(event);
        String dashboardBody = JSON.toJSONString(event.policy);


        new Thread(()->{coreRequest(DashboardURL,dashboardBody);}).start();
        new Thread(()->{coreRequest(SearchURL,searchBody);}).start();
        new Thread(()->{coreRequest(DocumentsURL,documentsBody);}).start();

				try{
						Thread.currentThread().sleep(1000);
				}catch(Exception e){
					System.out.println("Exception went in posting");
				}
    }

    public static void policyTerminatedEvent(String policyNumber, PolicyTerminatedEvent event){
        String URL = "http://172.17.0.1:10001/api/v1/web/guest/poc/serverless-policy-search-service/policies/terminate";
        String body = JSON.toJSONString(event.policy);
        coreRequest(URL,body);
    }
}
