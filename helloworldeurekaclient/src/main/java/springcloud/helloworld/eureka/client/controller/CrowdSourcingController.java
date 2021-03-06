package springcloud.helloworld.eureka.client.controller;

import com.fasterxml.jackson.databind.util.ArrayBuilders;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URI;
import java.util.Base64;
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

    @RequestMapping(value = "/getOneImage", method = RequestMethod.GET)
    public String getOneImage(@RequestParam("itemId") String id) {
        String result = "Not Found: ";
        try {
            //File image = loadFile("srcImage/" + id + ".jpg");
            //FileInputStream fin = new FileInputStream(image);
            InputStream fin = loadInputStream("srcImage/" + id + ".jpg");
            ByteArrayOutputStream bout = new ByteArrayOutputStream();

            int r;
            byte[] bytes = new byte[1024];
            while ((r = fin.read(bytes)) > 0) {
                bout.write(bytes, 0, r);
            }
            result = Base64.getEncoder().encodeToString(bout.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return result + e.toString();
        }
        return result;
    }

    @RequestMapping(value = "/putOneImage", method = RequestMethod.POST)
    public String putOneImage(@RequestParam("itemId") String id, @RequestBody String body) {
        try {
            File f = new File(id + ".jpg");

            FileOutputStream fout = new FileOutputStream(f);

            byte[] bytes = Base64.getDecoder().decode(body);
            fout.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();

            return "FAILED-2: " + e.toString();
        }
        return "SUCCESS";
    }

    private static InputStream loadInputStream(String filename) throws IOException {
        return new ClassPathResource(
                filename).getInputStream();
    }
}

