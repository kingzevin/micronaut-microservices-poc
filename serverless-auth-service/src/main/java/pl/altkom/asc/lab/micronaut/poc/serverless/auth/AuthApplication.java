package pl.altkom.asc.lab.micronaut.poc.serverless.auth;

import pl.altkom.asc.lab.micronaut.poc.serverless.auth.DataLoader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.micronaut.runtime.Micronaut;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AuthApplication {
    private static boolean initFlag = false;
    private static int port = 8090;

    public static void main(String[] args) {
        Micronaut.run(AuthApplication.class);
    }

    public static JsonObject main(JsonObject args) throws Exception{

				//System.out.println(args);

        if(!initFlag){

            // initialize in another thread.
            Thread listenThread = new Thread(()->{
                String[] s = {""};
                AuthApplication.main(s);
            });
            listenThread.start();
            // await initialization.
            synchronized(DataLoader.initializedFlag){
                DataLoader.initializedFlag.wait();
            }
            initFlag = true;
        }

        JsonObject result = new JsonObject();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response;
        // URI uri = new URIBuilder();
        if(args.has("__ow_method")){
            String url = "http://localhost:" + port + args.get("__ow_path").getAsString();
            if(args.get("__ow_query").getAsString().length() > 0){
                url += "?" + args.get("__ow_query").getAsString();
            }
            String headers = new Gson().toJson(args.getAsJsonObject("__ow_headers"));
            String body = args.get("__ow_body").getAsString();

            if(body.length() > 0){
                // body exists
                if(Base64.isBase64(body.getBytes())){
                    body = new String(Base64.decodeBase64(body));
                }
            }

						//System.out.println(body);

            switch (args.get("__ow_method").getAsString()){
                case "get":
                {
                    HttpGet request = new HttpGet(url);
                    setHeaders(request, headers);
                    response = httpClient.execute(request);
                    break;
                }
                case "post":
                {
										System.out.println("post");
                    HttpPost request = new HttpPost(url);
                    setHeaders(request, headers);
                    request.setEntity(new StringEntity(body));
                    response = httpClient.execute(request);
                    System.out.println(response);
                    break;
                }
                default:
                    result.addProperty("body", "No method is found!");
                    return result;
            }
            setHeaders(result, response);
            result.addProperty("body", EntityUtils.toString(response.getEntity(), Charsets.UTF_8));
            result.addProperty("statusCode", response.getStatusLine().getStatusCode());

            response.close();
            httpClient.close();
//            Spark.stop(); // zevin: a bug in openwhisk: the thread is not cleared and isolated
            return result;
        }
        else{
            result.addProperty("body", "No method is found!");
            return result;
        }
    }

    private static void setHeaders(HttpRequestBase request, String headers){
        // Dependency bug with: JsonObject.keySet()
        //      compile passed while no method found when running.
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(headers);
            for(Object key : jsonObject.keySet()){
                request.setHeader(String.valueOf(key), String.valueOf(jsonObject.get(key)));
            }
        } catch (ParseException e) {
            System.out.println("ParseError!");
            e.printStackTrace();
        }
    }

    private static void setHeaders(JsonObject result, CloseableHttpResponse response){
        JsonObject headers = new JsonObject();

        for(Header h : response.getAllHeaders()){
            headers.addProperty(h.getName(), h.getValue());
        }

        result.add("headers", headers);
    }
}
