package com.ditis.recp.dataaccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ditis.recp.business.model.entity.PatternEntity;

@Repository
public interface PatternRepository extends JpaRepository<PatternEntity, Long>  {
	public List<PatternEntity> findByName(String name);
}
