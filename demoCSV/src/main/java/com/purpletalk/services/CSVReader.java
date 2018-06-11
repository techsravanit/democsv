package com.purpletalk.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.purpletalk.entities.TestEmp;

@Service
public class CSVReader {
	
	private final String filePath="/home/kisor/Desktop/test_emp.csv";
	
	public List<TestEmp> readCSV(){
		BufferedReader reader=null;
		CSVParser parser=null;
		String firstName=null;
		String lastName=null;
		String salary=null;
		TestEmp emp=null;
		List<TestEmp> li=new ArrayList<>();
		try{
			File f = new File(filePath);

			if(f.exists()){
				System.out.println("File existed");

				reader=new BufferedReader(new FileReader(f));
				parser=new CSVParser(reader, CSVFormat.DEFAULT.withHeader("firstname","lastname","salary").withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
				
				for(CSVRecord record:parser){
					emp=new TestEmp();

					firstName=record.get("firstname");
					lastName=record.get("lastname");
					salary=record.get("salary");

					emp.setFirstName(firstName);
					emp.setLastName(lastName);
					emp.setSalary(salary);
					li.add(emp);
				}
			}else{
				  System.out.println("File not found!");
			  }
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
		return li;
	}
}
