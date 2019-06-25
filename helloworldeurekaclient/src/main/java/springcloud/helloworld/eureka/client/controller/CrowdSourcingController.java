package springcloud.helloworld.eureka.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@Configuration
public class CrowdSourcingController {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    @RequestMapping("/getAll")
    public String getAll(){
        RestTemplate rt = getRestTemplate();
        String result = rt.getForObject("http://CrowdSourcing/getAll", String.class);
        return result;
    }
    @RequestMapping("/getAl")
    public String getAl(){
        return "hehe";
    }
}
