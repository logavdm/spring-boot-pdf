package com.loga.work;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DataPopulate {
	
	
	private static final int recordCount=100;
			
	public DataPopulate(NamedParameterJdbcTemplate jdbcTemplate) {	
		try {
			String query="INSERT INTO STUDENT(ID,NAME,CREATED,UPDATED) VALUES(:id,:name,now(),now())";		
			jdbcTemplate.update("DELETE FROM STUDENT",new LinkedHashMap<>());				
			for (int i = 1; i < recordCount; i++) {			
				Map<String,Object> params=new LinkedHashMap<>();
				params.put("id",i);
				params.put("name","loga"+i);
				jdbcTemplate.update(query, params);
			}		
		}catch (Exception e) {
			System.err.println("Exception when data populate :: "+e.getMessage());
		}finally {
			System.out.println("insert completed");
		}			
	}
	
	
}
