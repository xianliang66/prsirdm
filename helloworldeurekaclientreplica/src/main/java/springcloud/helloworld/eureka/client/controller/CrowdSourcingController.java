package springcloud.helloworld.eureka.client.controller;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Configuration
public class CrowdSourcingController {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    @RequestMapping(value = "/getOneImage", method = RequestMethod.GET)
    public String getOneImage(@RequestParam("itemId") String id) {
        RestTemplate rt = getRestTemplate();
        String result = rt.getForObject("http://CrowdSourcing/getOneImage?itemId={id}", String.class, id);
        return result;
    }

    @RequestMapping(value = "/putOneImage", method = RequestMethod.POST)
    public String putOneImage(@RequestParam("itemId") String id, @RequestBody String body) {
        RestTemplate rt = getRestTemplate();
        String result = rt.postForObject("http://CrowdSourcing/putOneImage?itemId="+id, body, String.class);
        return result;
    }
}
