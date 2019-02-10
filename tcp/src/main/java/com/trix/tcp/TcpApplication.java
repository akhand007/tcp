package com.trix.tcp;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TcpApplication {

	@Autowired
	BroadCastTCPServer tcp;
	
    public static void main(String[] args) throws Exception {
        		SpringApplication.run(TcpApplication.class, args);
        		
    }
    
    @PostConstruct
    public void starter() throws IOException {
		tcp.startServer("8888");
    }
}

