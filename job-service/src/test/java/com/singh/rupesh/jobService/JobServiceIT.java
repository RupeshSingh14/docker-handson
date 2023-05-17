package com.singh.rupesh.jobService;

import com.singh.rupesh.jobService.dto.JobDto;
//import com.singh.rupesh.jobService.generic.BaseTest;
import com.singh.rupesh.jobService.compose.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

//Use gradle clean test to run this from cmd
@SpringBootTest
@AutoConfigureWebTestClient
class JobServiceIT extends BaseTest {

	@Autowired
	private WebTestClient client;

	@Test
	public void allJobsTest() {
		this.client.get()
				.uri("/job/all")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				//.consumeWith(e -> System.out.println(new String(e.getResponseBody())))
				.jsonPath("$").isNotEmpty();
	}

	@Test
	public void searchBySkillsTest() {
		this.client.get()
				.uri("/job/search?skills=java")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				//.consumeWith(e -> System.out.println(new String(e.getResponseBody())))
				.jsonPath("$.size()").isEqualTo(4);
	}

	@Test
	public void postJobTest() {
		var dto = JobDto.create("Devops Engineer", "Google", Set.of("K8's", "Docker", "Ansible"), 4000, true, null);
		this.client.post()
				.uri("/job")
				.bodyValue(dto)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				//.consumeWith(e -> System.out.println(new String(e.getResponseBody())))
				.jsonPath("$").isNotEmpty();
	}

}