package sn.ept.brt.tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RealtimeTrackingApplication {
	public static void main(String[] args) {
		SpringApplication.run(RealtimeTrackingApplication.class, args);
	}
}