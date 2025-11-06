package com.edistrict.pcpndt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.edistrict.pcpndt")
public class PcpndtApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcpndtApplication.class, args);
	}

}
