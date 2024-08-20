package api.resourses;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import api.pojo.DietitianPostPojo;
import api.utils.ExcelReader;



public class DietitianPostdata {

	static ExcelReader er = new ExcelReader();
	
	
	 public static  DietitianPostPojo createdietitiandata(String sheetname, int rownum) throws IOException {
		
		 DietitianPostPojo creatDietitian = new DietitianPostPojo();
		 
		 Map<String, String> dataMap;
		 dataMap = ExcelReader.getTestData(sheetname,rownum);
			
		 creatDietitian.setContact_Number(dataMap.get("ContactNumber"));
		 creatDietitian.setDate_Of_Birth(dataMap.get("DateOfBirth"));
		 creatDietitian.setEducation(dataMap.get("Education"));
		 creatDietitian.setEmail(dataMap.get("Email"));
		 creatDietitian.setFirst_name(dataMap.get("Firstname"));
		 creatDietitian.setHospital_City(dataMap.get("HospitalCity"));
		 creatDietitian.setHospital_Name(dataMap.get("HospitalName"));
		 creatDietitian.setHospital_Pincode(dataMap.get("HospitalPincode"));
		 creatDietitian.setHospital_Street(dataMap.get("HospitalStreet"));
		 creatDietitian.setLast_name(dataMap.get("Lastname"));
											
		 
		 /*
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
		 */
		
		return creatDietitian;
		
	}
	 
	
	 	
}
