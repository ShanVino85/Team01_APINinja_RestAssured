package api.resourses;

import java.io.IOException;
import api.pojo.PatientPostPojo;
import api.utils.ExcelReader;

public class PatientPostdata {
	
	static ExcelReader er = new ExcelReader();

	public  PatientPostPojo ExcelValiddata() throws IOException  {
		
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
	
	
public  PatientPostPojo ExcelonlyMandatorydata() throws IOException  {
		
		PatientPostPojo Createpatientpost = new PatientPostPojo();
		
		Createpatientpost.setFirstName(er.getCellData("PatientPost",2,0));
		Createpatientpost.setLastName(er.getCellData("PatientPost",2,1));
		Createpatientpost.setContactNumber(er.getCellData("PatientPost",2,2));
		Createpatientpost.setEmail(er.getCellData("PatientPost",2,3));
		Createpatientpost.setAllergy(er.getCellData("PatientPost",2,4));
		Createpatientpost.setFoodPreference(er.getCellData("PatientPost",2,5));
		Createpatientpost.setCuisineCategory(er.getCellData("PatientPost",2,6));
		Createpatientpost.setDateOfBirth(er.getCellData("PatientPost",2,7));
		
		
		return Createpatientpost;
	}
	
	public String Patienttokencreation(String PT) {
		 
		return "{\n  \"password\": \"test\",\n  \"userLoginEmail\": \""+PT+"\"\n}";
	
		}
	
public  PatientPostPojo OnlyValiddataNeg() throws IOException  {
		
		PatientPostPojo Createpatientpost = new PatientPostPojo();
		Createpatientpost.setAllergy(er.getCellData("PatientPost",1,4));
		Createpatientpost.setFoodPreference(er.getCellData("PatientPost",1,5));
		Createpatientpost.setCuisineCategory(er.getCellData("PatientPost",1,6));
		return Createpatientpost;
	}

public  PatientPostPojo onlyinvalidMandatorydataNeg() throws IOException  {
	
	PatientPostPojo Createpatientpost = new PatientPostPojo();
	
	Createpatientpost.setFirstName(er.getCellData("PatientPost",5,0));
	Createpatientpost.setLastName(er.getCellData("PatientPost",5,1));
	Createpatientpost.setContactNumber(er.getCellData("PatientPost",5,2));
	Createpatientpost.setEmail(er.getCellData("PatientPost",5,3));
	Createpatientpost.setAllergy(er.getCellData("PatientPost",5,4));
	Createpatientpost.setFoodPreference(er.getCellData("PatientPost",5,5));
	Createpatientpost.setCuisineCategory(er.getCellData("PatientPost",5,6));
	Createpatientpost.setDateOfBirth(er.getCellData("PatientPost",5,7));
	
	
	return Createpatientpost;
}
	
public  PatientPostPojo validManinvalidAddNeg() throws IOException  {
	
	PatientPostPojo Createpatientpost = new PatientPostPojo();
	
	Createpatientpost.setFirstName(er.getCellData("PatientPost",6,0));
	Createpatientpost.setLastName(er.getCellData("PatientPost",6,1));
	Createpatientpost.setContactNumber(er.getCellData("PatientPost",6,2));
	Createpatientpost.setEmail(er.getCellData("PatientPost",6,3));
	Createpatientpost.setAllergy(er.getCellData("PatientPost",6,4));
	Createpatientpost.setFoodPreference(er.getCellData("PatientPost",6,5));
	Createpatientpost.setCuisineCategory(er.getCellData("PatientPost",6,6));
	Createpatientpost.setDateOfBirth(er.getCellData("PatientPost",6,7));
	
	
	return Createpatientpost;
}
	

}
