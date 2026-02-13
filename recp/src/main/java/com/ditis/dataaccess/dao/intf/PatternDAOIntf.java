package com.ditis.dataaccess.dao.intf;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ditis.recp.business.model.entity.PatternEntity;

/**
 *    @author Rindra Mbolamananamalala
 */
@Repository
public interface PatternDAOIntf extends JpaRepository<PatternEntity, Long>  {
	/**
	 * Fetching from the Pattern DB the list of all the patterns (entity) having the same name as that of the parameter "patternName"'s value
	 * @param patternName The name of the patterns to be fetched from the Pattern DB   
	 * @return The list of all the patterns (entity) having the same name as that of the parameter "patternName"'s value
	 */
	public List<PatternEntity> findByName(String patternName);
	
	/**
	 * Fetching from the Pattern DB the list of all the patterns (entity) having the same Problem Statement as that of the parameter "problemStatement"'s 
	 * value
	 * @param problemStatement The problem statement of the patterns to be fetched from the Pattern DB   
	 * @return The list of all the patterns (entity) having the same Problem Statement  as that of the parameter "problemStatement"'s value
	 */
	public List<PatternEntity> findByProblem(String problemStatement);
}
