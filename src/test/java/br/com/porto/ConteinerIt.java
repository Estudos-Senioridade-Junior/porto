package br.com.porto;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

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
import static io.restassured.RestAssured.*;
import br.com.porto.Utils.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class ConteinerIt {

	@LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;
	
	private String conteiner;
	
	private String identificacaoIncorreta;
	
	private static final int conteinerId = 100000000;
	
	@BeforeEach
	public void setUp() {
		enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath=("/conteiner");
		RestAssured.port=port;
		
		conteiner = ResourceUtils.getContentFromResource(
				"/JsonData/conteiner-identificacao-correta.json");
		
		identificacaoIncorreta = ResourceUtils.getContentFromResource(
				"/JsonData/conteiner-identificacao-incorreta.json");
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarConteineres() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus404_QuandoNaoExistirConteinerNoBanco() {
		given()
			.accept(ContentType.JSON)
			.pathParam("conteinerId", conteinerId)
		.when()
			.get("/{conteinerId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarStatus400_QuandoTentarGravarConteinerComIdentficacaoIncorreta() {
		given()
			.accept(ContentType.JSON)
			.body(identificacaoIncorreta)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test	
	public void deveRetornarStatus201_QuandoTentarGravarConteinerComIdentificacaoCorreta() {
		given()
			.accept(ContentType.JSON)
			.body(conteiner)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	
	
}
