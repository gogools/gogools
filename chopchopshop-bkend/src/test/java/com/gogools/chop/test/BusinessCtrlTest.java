package com.gogools.chop.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.gogools.chop.dto.ProductDTO;
import com.gogools.rest.RestResponseDTO;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Import(com.gogools.chop.mapper.ProductMapper.class)
public class BusinessCtrlTest {

	@LocalServerPort
	private int			port;

	TestRestTemplate	restTemplate	= new TestRestTemplate();
	HttpHeaders			headers			= new HttpHeaders();

	/*@Autowired
	private MockMvc mvc;
	
	
	@Test
	public void updateCatalogTest() throws Exception {
		
		String filePath = getClass().getClassLoader().getResource("test-catalog.xlsx").getFile();
		
		MockMultipartFile file = new MockMultipartFile("excel", "test-catalog.xlsx", MediaType.MULTIPART_FORM_DATA_VALUE, Files.readAllBytes(new File( filePath ).toPath()));
		
		mvc.perform(multipart("/chop/createCatalog").file(file))
			.andExpect(status().isOk());
	}*/

	/**
	 * This is just a test call
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		
		HttpEntity<String> entity = new HttpEntity<String>("", headers);
		ResponseEntity<RestResponseDTO<List<String>>> response = restTemplate.exchange("http://localhost:" + port + "/chop/test", 
																				HttpMethod.POST, 
																				entity, 
																				new ParameterizedTypeReference<RestResponseDTO<List<String>>>() {});
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	/**
	 * This one tries to make a bulk load of an Excel catalog and then extracts a product with WS-BT27 code
	 * @throws Exception
	 */
	@Test
	public void updateCatalogTest() throws Exception {

		ResponseEntity<RestResponseDTO<List<ProductDTO>>> response = restTemplate.exchange("http://localhost:" + port + "/chop/bulkCatalog", 
																				HttpMethod.POST, 
																				getFile2Test("excel","test-catalog.xlsx"), 
																				new ParameterizedTypeReference<RestResponseDTO<List<ProductDTO>>>() {});
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertTrue(!response.getBody().getData().isEmpty());
		assertEquals(48, response.getBody().getData().size());
		
		ResponseEntity<RestResponseDTO<ProductDTO>> response2 = restTemplate.exchange("http://localhost:" + port + "/chop/product/code/WS-BT27", 
																				HttpMethod.GET, 
																				null, 
																				new ParameterizedTypeReference<RestResponseDTO<ProductDTO>>() {});

		assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response2.getBody().getData().getCode()).isEqualTo("WS-BT27");
		assertThat(response2.getBody().getData().getBrandId()).isEqualTo("WEISS SCHWARZ".toLowerCase());
	}
	
	/**
	 * This test deliberately try to attempt a error call by sending an empty Excel catalog 
	 * @throws Exception
	 */
	@Test
	public void errorCatalogTest() throws Exception {

		ResponseEntity<RestResponseDTO<List<ProductDTO>>> response = restTemplate.exchange("http://localhost:" + port + "/chop/bulkCatalog", 
																				HttpMethod.POST, 
																				getFile2Test("excel", "empty-catalog.xlsx"), 
																				new ParameterizedTypeReference<RestResponseDTO<List<ProductDTO>>>() {});
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(response.getBody().getMsg()).isEqualTo("Nothing was updated");
	}
	
	/**
	 * This test call performs product extraction by API calls
	 * @throws Exception
	 */
	@Test
	public void getProductTest() throws Exception {

		ResponseEntity<RestResponseDTO<ProductDTO>> response = restTemplate.exchange("http://localhost:" + port + "/chop/product/code/WS-BT27", 
																				HttpMethod.GET, 
																				null, 
																				new ParameterizedTypeReference<RestResponseDTO<ProductDTO>>() {});
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getData().getCode()).isEqualTo("WS-BT27");
		assertThat(response.getBody().getData().getBrandId()).isEqualTo("WEISS SCHWARZ".toLowerCase());
		
		String id = response.getBody().getData().getId();
		
		response = restTemplate.exchange("http://localhost:" + port + "/chop/product/code/WS-BT000", 
										HttpMethod.GET, 
										null, 
										new ParameterizedTypeReference<RestResponseDTO<ProductDTO>>() {});
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody().getData()).isNull();
		
		response = restTemplate.exchange("http://localhost:" + port + "/chop/product/id/" + id,
										HttpMethod.GET, 
										null, 
										new ParameterizedTypeReference<RestResponseDTO<ProductDTO>>() {});
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getData().getBrandId()).isEqualTo("WEISS SCHWARZ".toLowerCase());
		
		response = restTemplate.exchange("http://localhost:" + port + "/chop/product/code/", 
										HttpMethod.GET, 
										null, 
										new ParameterizedTypeReference<RestResponseDTO<ProductDTO>>() {});
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	
	/**
	 * This method attempts to add a new product by API call
	 * @throws Exception
	 */
	@Test
	public void addProductTest() throws Exception {
		
		ResponseEntity<RestResponseDTO<ProductDTO>> response = restTemplate.exchange("http://localhost:" + port + "/chop/product", 
																				HttpMethod.POST, 
																				null, 
																				new ParameterizedTypeReference<RestResponseDTO<ProductDTO>>() {});
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);		
		assertTrue(!response.hasBody());
		
		ProductDTO p = new ProductDTO();
		p.setBrandId("dgadgfsg44w3");
		p.setCode("TESTCODE");
		p.setName("test name");
		p.setDescrip("this is a test description");
		p.setPrice(-10.1);
		p.setQty(5);
		p.setType("Test Type");
		
		HttpEntity<ProductDTO> entity = new HttpEntity<ProductDTO>(p);
		
		response = restTemplate.exchange("http://localhost:" + port + "/chop/product", 
										HttpMethod.POST, 
										entity, 
										new ParameterizedTypeReference<RestResponseDTO<ProductDTO>>() {});

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		p.setPrice(10.1);
		entity = new HttpEntity<ProductDTO>(p);
		
		response = restTemplate.exchange("http://localhost:" + port + "/chop/product", 
										HttpMethod.POST, 
										entity, 
										new ParameterizedTypeReference<RestResponseDTO<ProductDTO>>() {});
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody().getData().getId()).isNotNull();
		
		ProductDTO newProduct = response.getBody().getData();
		response = restTemplate.exchange("http://localhost:" + port + "/chop/product", 
										HttpMethod.POST, 
										new HttpEntity<ProductDTO>(newProduct), 
										new ParameterizedTypeReference<RestResponseDTO<ProductDTO>>() {});
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);	
		assertThat(response.getBody().getMsg()).isEqualTo("Product code already exist");	
	}
	
	/**
	 * This method attempts to update a product by API call
	 * @throws Exception
	 */
	@Test
	public void updateProductTest() throws Exception {
		
		ResponseEntity<RestResponseDTO<ProductDTO>> response = restTemplate.exchange("http://localhost:" + port + "/chop/product/test", 
																				HttpMethod.PUT, 
																				null, 
																				new ParameterizedTypeReference<RestResponseDTO<ProductDTO>>() {});
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);		
		assertTrue(!response.hasBody());		
		
		response = restTemplate.exchange("http://localhost:" + port + "/chop/product/test", 
										HttpMethod.PUT, 
										new HttpEntity<ProductDTO>(new ProductDTO()), 
										new ParameterizedTypeReference<RestResponseDTO<ProductDTO>>() {});
		
		ProductDTO p = new ProductDTO();
		p.setBrandId("fseses443");
		p.setCode("TESTCODE");
		p.setName("test name");
		p.setDescrip("this is a test description");
		p.setPrice(10.1);
		p.setQty(5);
		p.setType("Test Type");
		
		response = restTemplate.exchange("http://localhost:" + port + "/chop/product", 
										HttpMethod.PUT, 
										new HttpEntity<ProductDTO>(p), 
										new ParameterizedTypeReference<RestResponseDTO<ProductDTO>>() {});
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);		
		assertThat(response.getBody().getMsg()).isEqualTo("Product is null or id is missing");	
		
		response = restTemplate.exchange("http://localhost:" + port + "/chop/product/code/WS-BT27", 
										HttpMethod.GET, 
										null, 
										new ParameterizedTypeReference<RestResponseDTO<ProductDTO>>() {});
		
		p.setId(response.getBody().getData().getId());
		
		response = restTemplate.exchange("http://localhost:" + port + "/chop/product", 
										HttpMethod.PUT, 
										new HttpEntity<ProductDTO>(p), 
										new ParameterizedTypeReference<RestResponseDTO<ProductDTO>>() {});
		
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);		
		assertThat(response.getBody().getMsg()).isEqualTo("Product updated");
		assertThat(response.getBody().getData().getDescrip()).isEqualTo("this is a test description");
	}
	
	
	private HttpEntity<MultiValueMap<String, Object>> getFile2Test(String paramName, String fileName) {
	
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		URL url = getClass().getClassLoader().getResource( fileName );

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add(paramName, new FileSystemResource( url != null ? new File(url.getFile()): new File("")));

		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(body, headers);
		
		return entity;
	}
}
