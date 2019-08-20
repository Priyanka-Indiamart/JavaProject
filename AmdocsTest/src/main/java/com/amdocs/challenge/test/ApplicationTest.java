package com.amdocs.challenge.test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {
	private static List<Article> articles = new ArrayList<Article>();
	private static ArticleService service = new ArticleService();
	@Autowired
	private MockMvc mockMvc;

	@BeforeClass
	public static void populateArticles() {
		articles.add(new Article("10 things that you thought were unhealthy"));
		articles.add(new Article("You won't sleep until you read this"));
		articles.add(new Article("I ran out of catchy titles"));
	}

	@Before
	public void clearDB() {
		this.service.clear();
	}

	public void addArticles() {
		for (Article article : articles) {
			this.service.add(article);
		}
	}

	@Test
	public void shouldRetrieveNothingFromEmptyDatabase() throws Exception {
		this.mockMvc.perform(get("/articles")).andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void shouldRetrievePostedArticles() throws Exception {
		addArticles();
		this.mockMvc.perform(get("/articles")).andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(articles.size())));
	}

	@Test
	public void shouldAllowUsToFindArticles() throws Exception {
		addArticles();
		Article article = this.service.getAll().get(0);
		this.mockMvc.perform(get("/articles/" + article.getId())).andExpect(jsonPath("id", is(article.getId())))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldHandleGetNotFound() throws Exception {
		System.out.println("<PROP::hidden>true");
		this.mockMvc.perform(get("/articles/10")).andExpect(status().isNotFound());
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
