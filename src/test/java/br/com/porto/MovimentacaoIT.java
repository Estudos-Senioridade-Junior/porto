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
public class MovimentacaoIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;
	
	private String movimentacao;
	
	private String movimentacaoDesacordo;
	
	private static final int movimentacaoId = 100000000;
	
	@BeforeEach
	public void setUp() {
		enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath=("/movimentacao");
		RestAssured.port=port;
		
		movimentacao = ResourceUtils.getContentFromResource(
				"/JsonData/movimentacao.json");
		
		movimentacaoDesacordo = ResourceUtils.getContentFromResource(
				"/JsonData/movimentacao-tipo-desacordo.json");
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarMovimentacao() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
			
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarMovimentacaoInexistenteNobanco() {
		given()
			.accept(ContentType.JSON)
			.pathParam("movimentacaoId", movimentacaoId)
		.when()
			.get("/{movimentacaoId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarNovaMovimentacao() {
		given()
			.accept(ContentType.JSON)
			.body(movimentacao)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarStatus400_QuantoTipoEmDesacordo() {
		given()
			.accept(ContentType.JSON)
			.body(movimentacaoDesacordo)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	

}
