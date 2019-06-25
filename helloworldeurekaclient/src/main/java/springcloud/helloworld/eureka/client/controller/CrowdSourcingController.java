package springcloud.helloworld.eureka.client.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CrowdSourcingController {
    @RequestMapping(value = "/getAll", method = GET, produces = "application/json")
    public String getAll() {
        return "hehe";
    }
    @RequestMapping(value = "/getByID", method = GET, produces = "application/json")
    public String getByID(Long ID) {
        return String.valueOf(ID) + "aaaa";
    }
}
