package com.joojeongyong.restdocs.example.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest
@ExtendWith(value = {RestDocumentationExtension.class, SpringExtension.class})
class HelloControllerTest {
	
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp(WebApplicationContext applicationContext, RestDocumentationContextProvider documentationContextProvider) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
			.apply(MockMvcRestDocumentation.documentationConfiguration(documentationContextProvider))
			.build();
	}
	
	@Test
	void hello() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.content().string("Hello World!"))
			.andDo(MockMvcRestDocumentation.document("hello"));
	}
}