package com.ditis.dataaccess.dao.intf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ditis.recp.business.model.entity.PatternEntity;
import com.ditis.recp.dataaccess.PatternRepository;

/**
 * The Abstract Class (interface) regrouping any DAO(Data Access Object)-based service related to a pattern 
 * (Application of the DAO pattern on a "pattern" object). 
 */
@Service
public interface PatternDAOIntf {
	
	//public PatternEntity createPattern(PatternEntity patternEntity);
	
	public List<PatternEntity> findByName(String name);
}
