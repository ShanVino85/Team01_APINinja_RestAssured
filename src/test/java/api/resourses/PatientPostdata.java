package api.resourses;

import java.io.IOException;
import api.pojo.PatientPostPojo;
import api.utils.ExcelReader;

public class PatientPostdata {
	
	static ExcelReader er = new ExcelReader();

	public  PatientPostPojo Exceldata() throws IOException  {
		
		PatientPostPojo Createpatientpost = new PatientPostPojo();
		
		Createpatientpost.setFirstName(er.getCellData("PatientPost",1,0));
		Createpatientpost.setLastName(er.getCellData("PatientPost",1,1));
		Createpatientpost.setContactNumber(er.getCellData("PatientPost",1,2));
		Createpatientpost.setEmail(er.getCellData("PatientPost",1,3));
		Createpatientpost.setAllergy(er.getCellData("PatientPost",1,4));
		Createpatientpost.setFoodPreference(er.getCellData("PatientPost",1,5));
		Createpatientpost.setCuisineCategory(er.getCellData("PatientPost",1,6));
		Createpatientpost.setDateOfBirth(er.getCellData("PatientPost",1,7));
		//Createpatientpost.setDateOfBirth("1992-02-14");
		
		
		return Createpatientpost;
	}
	
	public String Patienttokencreation(String PT) {
		 
		return "{\n  \"password\": \"test\",\n  \"userLoginEmail\": \""+PT+"\"\n}";
	
		}

}
