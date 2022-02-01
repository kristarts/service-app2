package com.softserve.itacademy.vkhomenko.serviceapp2.integration;

import org.junit.Test;

import java.net.http.HttpClient;

public class IntegrationTestSuite {

    String url = "http://localhost:8081";
    HttpClient client = HttpClient.newHttpClient();
    //String userName = "def";
    //String userEmail = "test@test.test";

    public String getBearerToken(String email, String password) {
        return "";
    }

    @Test
    public void getUserArticle() {

    }
}
