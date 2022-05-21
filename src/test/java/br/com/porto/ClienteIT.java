package br.com.porto;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.porto.Utils.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class ClienteIT {

	@LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;
	
	private String cliente;
	
	private static final int clienteId = 100000000;
	
	@BeforeEach
	public void setUp() {
		enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath=("/cliente");
		RestAssured.port=port;
		
		cliente = ResourceUtils.getContentFromResource(
				"/JsonData/cliente.json");
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarClientes() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus404_QuandoClienteNaoExistirNoBanco() {
		given()
			.accept(ContentType.JSON)
			.pathParam("clienteId", clienteId)
		.when()
			.get("/{clienteId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarCliente() {
		given()
			.accept(ContentType.JSON)
			.body(cliente)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	
}
