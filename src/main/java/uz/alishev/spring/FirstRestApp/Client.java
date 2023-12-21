import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {
    public static void main(String[] args){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> jsonToSendSensor = new HashMap<>();
        jsonToSendSensor.put("name", "My sensorr2");

        HttpEntity<Map<String, String>> requestSensor = new HttpEntity<>(jsonToSendSensor);
        // String url = "https://reqres.in/api/users/2";
        String urlSensor = "http://localhost:8080/sensors/registration";
        String responseSensor = restTemplate.postForObject(urlSensor, requestSensor, String.class);
        float min = -100;
        float max = 100;
        String urlNew = "http://localhost:8080/measurements/add";
        for(int i=0;i<1000;i++) {
            Random r = new Random();
            Random rd = new Random();
            Map<String,Object> jsonToSend = new HashMap<>();
            float random = min + r.nextFloat() * (max - min);
            jsonToSend.put("temperature", random);
            jsonToSend.put("raining", rd.nextBoolean());
            jsonToSend.put("sensor", jsonToSendSensor);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(jsonToSend);
            // String url = "https://reqres.in/api/users/2";

            String response = restTemplate.postForObject(urlNew, request, String.class);
        }

        String url = "http://localhost:8080/measurements";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);


    }
}
