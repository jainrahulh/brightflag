package com.brightflag.codetest;



import com.brightflag.domain.Student;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentAPITest {

    HttpHeaders headers = new HttpHeaders();

    TestRestTemplate restTemplate = new TestRestTemplate();

    String url = "http://localhost:8080";
   

    @Test
    public void getAllStudentsTest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(url+"/getStudents");

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        JSONArray obj = new JSONArray(result.getBody());
        Assert.assertEquals(2, obj.length());
    }

    @Test
    public void getKnownStudentTest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(url+"/searchStudent/2");

        ResponseEntity<Student> result = restTemplate.getForEntity(uri, Student.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(2, Integer.parseInt(result.getBody().getStudentID().toString()));
        Assert.assertEquals("Jose", result.getBody().getFirstName());
        Assert.assertEquals("Rodriguez", result.getBody().getLastName());
    }

    @Test
    public void getUnknownStudentTest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(url+"/searchStudent/99");

        try {
            ResponseEntity<Student> result = restTemplate.getForEntity(uri, Student.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(404, e.getRawStatusCode());
        }
    }
}