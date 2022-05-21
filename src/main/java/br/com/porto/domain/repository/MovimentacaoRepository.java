package br.com.porto.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.porto.domain.model.Movimentacao;
import br.com.porto.domain.model.Relatorio;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{

	
	@Query(value = "select c.cliente_id as cliente, m.tipo as tipo, count(m.tipo) as total from movimentacao m inner join conteiner c where m.conteiner_id=c.id	group by \r\n"
			+ "m.tipo,c.cliente_id", nativeQuery = true)
	List<Relatorio> relatorioResumoMovimentacao();
	
}
