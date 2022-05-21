package br.com.porto.api.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.porto.api.model.RelatorioModel;
import br.com.porto.domain.model.Relatorio;
import br.com.porto.domain.service.CadastroConteinerService;
import br.com.porto.domain.service.CadastroMovimentacaoService;

@RestController
@RequestMapping("/relatorios")
public class RelatorioControler {
	
	@Autowired
	private CadastroMovimentacaoService cadastroMovimentacaoService;
	
	@Autowired
	private CadastroConteinerService cadastroConteinerService;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/resumomovimentacao")
	public RelatorioModel resumoMovimentacao() {
		
		List<Relatorio> relatorios = cadastroMovimentacaoService
				.getRelatorioResumoMovimentacao();//Pega o resultado da consulta no banco
		
		int totalImportacao = cadastroConteinerService.getImportacao();
		int totalExportacao = cadastroConteinerService.getExportacao();
		
		RelatorioModel relatorioModel = new RelatorioModel();
		relatorioModel.setRelatorio(relatorios);
		relatorioModel.setTotalImportacao(totalImportacao);
		relatorioModel.setTotalExportacao(totalExportacao);
		
		return relatorioModel;
	}

}
