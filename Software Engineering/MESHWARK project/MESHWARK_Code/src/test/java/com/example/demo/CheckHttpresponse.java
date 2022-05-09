package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckHttpresponse {
    @LocalServerPort
    private int Port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void ShouldPassStringMatches(){
        assertEquals("Hello World from Spring boot"
                ,testRestTemplate.getForObject("http://localhost:"+Port+"/",String.class));
    }
}
