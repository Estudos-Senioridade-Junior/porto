package br.com.porto.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.porto.domain.model.Conteiner;

@Repository
public interface ConteinerRepository extends JpaRepository<Conteiner, Long>{

	@Query(value="select count(*) from conteiner where categoria=0", nativeQuery =true)
	int consultaPorImportacao();
	
	@Query(value="select count(*) from conteiner where categoria=1", nativeQuery =true)
	int consultaPorExportacao();
}
