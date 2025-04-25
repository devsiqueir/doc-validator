package br.com.siqueiradev.doc_validator.domain.repository;

import br.com.siqueiradev.doc_validator.domain.entity.ConsultaLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaLogRepository extends JpaRepository<ConsultaLog, Long> {
}