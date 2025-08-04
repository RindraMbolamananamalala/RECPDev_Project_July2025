package com.ditis.recp.business.model.entity;

import java.lang.reflect.Field;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *    @author Rindra Mbolamananamalala
 */
public class RECPGenericEntity {
	
	/**
	 * @return the entire textual description made up of the Entity's attributes' values (in the String format) 
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
