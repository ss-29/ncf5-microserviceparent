package dev.newbcoder_f5.ncf5discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NCf5DiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NCf5DiscoveryServerApplication.class, args);
    }
    
}
