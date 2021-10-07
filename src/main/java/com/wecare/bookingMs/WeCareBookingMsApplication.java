package com.wecare.bookingMs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WeCareBookingMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeCareBookingMsApplication.class, args);
	}

}
