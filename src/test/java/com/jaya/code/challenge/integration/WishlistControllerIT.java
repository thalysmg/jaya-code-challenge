package com.jaya.code.challenge.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaya.code.challenge.domain.Product;
import com.jaya.code.challenge.domain.Wishlist;
import com.jaya.code.challenge.repository.ProductRepository;
import com.jaya.code.challenge.repository.WishlistRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WishlistControllerIT {
	private static final String WISHLIST_URL = "/wishlists";
	private static final String PRODUCT_ID_PARAM = "productId";

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WishlistRepository wishlistRepository;

	@Autowired
	private ProductRepository productRepository;

	private static final String ID_WS_1 = "64f60f1ef9082e291b406cdf";
	private static final String ID_WS_2 = "64f60f1ef9082e291b405deg";
	private static final String ID_WS_3 = "64f60f1ef9082e291b406efh";

	private static final String ID_P_1 = "64f2a77f36eaf55387545bg1";
	private static final String ID_P_2 = "64f2a77f36eaf55387545bg2";
	private static final String ID_P_3 = "64f2a77f36eaf55387545bg3";
	private static final String ID_P_4 = "64f2a77f36eaf55387545bg4";
	private static final String ID_P_5 = "64f2a77f36eaf55387545bg5";

	@BeforeEach
	public void init() {

		wishlistRepository.deleteAll();
		productRepository.deleteAll();

		List<Product> products = List.of(
			Product.builder().id(ID_P_1).name("Notebook").price(3213.99).build(),
			Product.builder().id(ID_P_2).name("Xbox One S").price(2800.99).build(),
	 		Product.builder().id(ID_P_3).name("Playstation 5").price(3599.99).build(),
			Product.builder().id(ID_P_4).name("Placa de Vídeo").price(1599.99).build()
		);
		productRepository.saveAll(products);

		List<Wishlist> lists = List.of(
			Wishlist.builder().id(ID_WS_1).owner("Teste 1").build(),
			Wishlist.builder().id(ID_WS_2).owner("Teste 2").products(List.of(products.get(0))).build()
		);
		wishlistRepository.saveAll(lists);
	}

	@Test
	public void testExistsProductInExistingList() throws Exception {
		this.mockMvc.perform(
			get(WISHLIST_URL+"/{id}?",ID_WS_2)
			.param(PRODUCT_ID_PARAM, ID_P_1)
		).andExpect(status().isOk());
	}

	@Test
	public void testExistsProductInListNotFound() throws Exception {
		this.mockMvc.perform(
			get(WISHLIST_URL+"/{id}", ID_WS_1)
			.param(PRODUCT_ID_PARAM, ID_P_2)
		).andExpect(status().isNotFound());
	}

	@Test
	public void testSaveNewWishListOk() throws Exception {
		Wishlist wishlist = Wishlist.builder().id(ID_WS_3).owner("Teste 3").build();
		this.mockMvc.perform(
			post(WISHLIST_URL).contentType(APPLICATION_JSON)
			.content(mapper.writeValueAsString(wishlist))
		).andExpect(status().isCreated());
	}

	@Test
	public void testSaveNewWishlistWithInvalidBody() throws Exception {
		Wishlist wishlist = Wishlist.builder().id(ID_WS_3).build();
		this.mockMvc.perform(
			post(WISHLIST_URL).contentType(APPLICATION_JSON)
			.content(mapper.writeValueAsString(wishlist))
		).andExpect(status().isBadRequest());
	}

	@Test
	public void testAddExistingProductInExistingList() throws Exception {
		this.mockMvc.perform(
			post(WISHLIST_URL+"/{id}", ID_WS_1)
			.param(PRODUCT_ID_PARAM, ID_P_4)
		).andExpect(status().isOk());
	}

	@Test
	public void testAddInexistentProductInExistingList() throws Exception {
		this.mockMvc.perform(
			post(WISHLIST_URL+"/{id}", ID_WS_1)
			.param(PRODUCT_ID_PARAM, ID_P_5)
		).andExpect(status().isBadRequest());
	}

	@Test
	public void testGetAllProductsFromExistingList() throws Exception {
		this.mockMvc.perform(
			get(WISHLIST_URL+"/{id}/products", ID_WS_2)
		).andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	public void testGetAllProductsFromExistingEmptyList() throws Exception {
		this.mockMvc.perform(
			get(WISHLIST_URL+"/{id}/products", ID_WS_1)
		).andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void testRemoveExistingProductFromList() throws Exception {
		this.mockMvc.perform(
			delete(WISHLIST_URL+"/{id}", ID_WS_2)
			.param(PRODUCT_ID_PARAM, ID_P_1)
		).andExpect(status().isNoContent());
	}

	@Test
	public void testRemoveProductWhichIsNotInList() throws Exception {
		this.mockMvc.perform(
			delete(WISHLIST_URL+"/{id}", ID_WS_2)
			.param(PRODUCT_ID_PARAM, ID_P_3)
		).andExpect(status().isBadRequest());
	}

	@Test
	public void testRemoveInexistingProductFromList() throws Exception {
		this.mockMvc.perform(
			delete(WISHLIST_URL+"/{id}", ID_WS_2)
			.param(PRODUCT_ID_PARAM, ID_P_5)
		).andExpect(status().isBadRequest());
	}
}
