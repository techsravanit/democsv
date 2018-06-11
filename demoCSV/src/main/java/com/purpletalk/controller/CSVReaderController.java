package com.purpletalk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purpletalk.entities.TestEmp;
import com.purpletalk.repositary.TestEmpRepositary;
import com.purpletalk.services.CSVReader;

@RestController
public class CSVReaderController {
	@Autowired
	private TestEmpRepositary repo;
	
	@Autowired
	private CSVReader csvReader;
	
	@RequestMapping("/")
	public void uploadCSVData(){
		List<TestEmp> listEmp=null;
		
		try{
			listEmp=csvReader.readCSV();
			if(listEmp!=null && listEmp.size()>0){
				for(TestEmp emp:listEmp){
					repo.save(emp);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
