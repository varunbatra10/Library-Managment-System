package com.library;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LibraryApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void a() {
		String json = "[{\"name\"	: \"Adam\"\r\n" + "},\r\n" + "{\"name\": \"Eve\"\r\n" + "},\r\n"
				+ "{\"name\": \"Louis\"\r\n" + "},\r\n" + "{\"name\": \"Adam\"\r\n" + "},\r\n"
				+ "{\"name\": \"John\"\r\n" + "},\r\n" + "{\"name\": \"Carl\"\r\n" + "},\r\n"
				+ "{\"name\": \"Steve\"\r\n" + "}\r\n" + "]";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(json, headers);

		restTemplate.exchange("/users", HttpMethod.POST, entity, String.class);

		json = "[{\"name\": \"Physics\",\r\n" + "\"totalQty\":4,\r\n" + "\"borrowedQty\":0\r\n" + "},\r\n"
				+ "{\"name\": \"Chemistry\",\r\n" + "\"totalQty\":2,\r\n" + "\"borrowedQty\":0\r\n" + "},\r\n"
				+ "{\"name\": \"Maths\",\r\n" + "\"totalQty\":2,\r\n" + "\"borrowedQty\":0\r\n" + "},\r\n"
				+ "{\"name\": \"English\",\r\n" + "\"totalQty\":1,\r\n" + "\"borrowedQty\":0\r\n" + "}\r\n" + "]";
		entity = new HttpEntity<>(json, headers);
		restTemplate.exchange("/books", HttpMethod.POST, entity, String.class);

	}

	@Test
	public void z() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		restTemplate.exchange("/users", HttpMethod.DELETE, entity, String.class);

		restTemplate.exchange("/books", HttpMethod.DELETE, entity, String.class);

	}

	@Test
	public void b() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>("", headers);

		ResponseEntity<String> response = restTemplate.exchange("/books/find", HttpMethod.GET, entity, String.class);

		String expectedJson = "[{\"id\":1,\"name\":\"Physics\",\"totalQty\":4,\"borrowedQty\":0},{\"id\":2,\"name\":\"Chemistry\",\"totalQty\":2,\"borrowedQty\":0},{\"id\":3,\"name\":\"Maths\",\"totalQty\":2,\"borrowedQty\":0},{\"id\":4,\"name\":\"English\",\"totalQty\":1,\"borrowedQty\":0}]";
		assertEquals(expectedJson, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void c() throws Exception {
		String json = "{\"bookId\":1,\r\n" + "\"userId\":1\r\n" + "}";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(json, headers);

		ResponseEntity<String> response = restTemplate.exchange("/books/borrow", HttpMethod.POST, entity, String.class);

		String expectedJson = "Book borrowed";
		assertEquals(expectedJson, response.getBody());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}

	@Test
	public void d() throws Exception {
		String json = "{\"bookId\":2,\r\n" + "\"userId\":1\r\n" + "}";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(json, headers);

		restTemplate.exchange("/books/borrow", HttpMethod.POST, entity, String.class);

		json = "{\"bookId\":3,\r\n" + "\"userId\":1\r\n" + "}";
		entity = new HttpEntity<>(json, headers);

		ResponseEntity<String> response = restTemplate.exchange("/books/borrow", HttpMethod.POST, entity, String.class);

		String expectedJson = "You can't borrow more than 2 books";
		assertEquals(expectedJson, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void e() throws Exception {
		String json = "{\"bookId\":2,\r\n" + "\"userId\":1\r\n" + "}";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(json, headers);

		ResponseEntity<String> response = restTemplate.exchange("/books/return", HttpMethod.POST, entity, String.class);

		String expectedJson = "Book is returned";
		assertEquals(expectedJson, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void f() throws Exception {
		String json = "{\"userId\":1\r\n" + "}";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(json, headers);

		ResponseEntity<String> response = restTemplate.exchange("/books/return", HttpMethod.POST, entity, String.class);

		String expectedJson = "All Books are returned";
		assertEquals(expectedJson, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

}
