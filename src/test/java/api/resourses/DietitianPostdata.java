package api.resourses;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import api.pojo.DietitianPostPojo;
import api.utils.ExcelReader;


public class DietitianPostdata {

	static ExcelReader er = new ExcelReader();

	 public static  DietitianPostPojo createdietitiandata() throws IOException {
		
		 DietitianPostPojo creatDietitian = new DietitianPostPojo();
		
		 creatDietitian.setContact_Number(er.getCellData("DietitianPost", 1, 0));
		 creatDietitian.setDate_Of_Birth(er.getCellData("DietitianPost", 1, 1));
		 creatDietitian.setEducation(er.getCellData("DietitianPost", 1, 2));
		 creatDietitian.setEmail(er.getCellData("DietitianPost", 1, 3));
		 creatDietitian.setFirst_name(er.getCellData("DietitianPost", 1, 4));
		 creatDietitian.setHospital_City(er.getCellData("DietitianPost", 1, 5));
		 creatDietitian.setHospital_Name(er.getCellData("DietitianPost", 1, 6));
		 creatDietitian.setHospital_Pincode(er.getCellData("DietitianPost", 1, 7));
		 creatDietitian.setHospital_Street(er.getCellData("DietitianPost", 1, 8));
		 creatDietitian.setLast_name(er.getCellData("DietitianPost", 1, 9));
		 
		
		return creatDietitian;
		/*
		 
		 HashMap d= new HashMap();
		 
		 d.put(er.getCellData("DietitianPost", 0, 0),er.getCellData("DietitianPost", 1, 0));
		 d.put(er.getCellData("DietitianPost", 0, 1),er.getCellData("DietitianPost", 1, 1));
		 d.put(er.getCellData("DietitianPost", 0, 2),er.getCellData("DietitianPost", 1, 2));
		 d.put(er.getCellData("DietitianPost", 0, 3),er.getCellData("DietitianPost", 1, 3));
		 d.put(er.getCellData("DietitianPost", 0, 4),er.getCellData("DietitianPost", 1, 4));
		 d.put(er.getCellData("DietitianPost", 0, 5),er.getCellData("DietitianPost", 1, 5));
		 d.put(er.getCellData("DietitianPost", 0, 6),er.getCellData("DietitianPost", 1, 6));
		 d.put(er.getCellData("DietitianPost", 0, 7),er.getCellData("DietitianPost", 1, 7));
		 d.put(er.getCellData("DietitianPost", 0, 8),er.getCellData("DietitianPost", 1, 8));
		 d.put(er.getCellData("DietitianPost", 0, 9),er.getCellData("DietitianPost", 1, 9));
		 return d;
		 */
	}
	 	
}
