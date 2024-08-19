package api.resourses;

import java.io.IOException;

import api.pojo.PatientPostPojo;
import api.utils.ExcelReader;

public class PatientPutdata {
	
	static ExcelReader er = new ExcelReader();

	public static  PatientPostPojo ExistingpdffilePUT() throws IOException  {
		
		PatientPostPojo Createpatientput1 = new PatientPostPojo();
		
		Createpatientput1.setFirstName(er.getCellData("PatientPut",1,0));
		Createpatientput1.setLastName(er.getCellData("PatientPut",1,1));
		Createpatientput1.setContactNumber(er.getCellData("PatientPut",1,2));
		Createpatientput1.setEmail(er.getCellData("PatientPut",1,3));
		Createpatientput1.setAllergy(er.getCellData("PatientPut",1,4));
		Createpatientput1.setFoodPreference(er.getCellData("PatientPut",1,5));
		Createpatientput1.setCuisineCategory(er.getCellData("PatientPut",1,6));
		Createpatientput1.setDateOfBirth(er.getCellData("PatientPut",1,7));
		//Createpatientpost.setDateOfBirth("1992-02-14");
		
		return Createpatientput1;
	}
	
	
public static  PatientPostPojo ExcelMandatorydataPUT() throws IOException  {
		
		PatientPostPojo Createpatientput1 = new PatientPostPojo();
		
		Createpatientput1.setFirstName(er.getCellData("PatientPut",2,0));
		Createpatientput1.setLastName(er.getCellData("PatientPut",2,1));
		Createpatientput1.setContactNumber(er.getCellData("PatientPut",2,2));
		Createpatientput1.setEmail(er.getCellData("PatientPut",2,3));
		Createpatientput1.setAllergy(er.getCellData("PatientPut",2,4));
		Createpatientput1.setFoodPreference(er.getCellData("PatientPut",2,5));
		Createpatientput1.setCuisineCategory(er.getCellData("PatientPut",2,6));
		Createpatientput1.setDateOfBirth(er.getCellData("PatientPut",2,7));
		//Createpatientpost.setDateOfBirth("1992-02-14");
		
		return Createpatientput1;
	}
public static  PatientPostPojo ValiddataAddPdfPUT() throws IOException  {
		
		PatientPostPojo Createpatientput1 = new PatientPostPojo();
		
		Createpatientput1.setFirstName(er.getCellData("PatientPut",3,0));
		Createpatientput1.setLastName(er.getCellData("PatientPut",3,1));
		Createpatientput1.setContactNumber(er.getCellData("PatientPut",3,2));
		Createpatientput1.setEmail(er.getCellData("PatientPut",3,3));
		Createpatientput1.setAllergy(er.getCellData("PatientPut",3,4));
		Createpatientput1.setFoodPreference(er.getCellData("PatientPut",3,5));
		Createpatientput1.setCuisineCategory(er.getCellData("PatientPut",3,6));
		Createpatientput1.setDateOfBirth(er.getCellData("PatientPut",3,7));
		//Createpatientpost.setDateOfBirth("1992-02-14");
		
		return Createpatientput1;
	}

public static  PatientPostPojo onlyaddfieldPUTNeg() throws IOException  {
	
	PatientPostPojo CreateputNeg = new PatientPostPojo();
	
	CreateputNeg.setAllergy(er.getCellData("PatientPut",3,4));
	CreateputNeg.setFoodPreference(er.getCellData("PatientPut",3,5));
	CreateputNeg.setCuisineCategory(er.getCellData("PatientPut",3,6));
	
	return CreateputNeg;
}

public static  PatientPostPojo onlyinvalidaddfieldPUTNeg() throws IOException  {
	
	PatientPostPojo CreateputNeg = new PatientPostPojo();
	
	CreateputNeg.setFirstName(er.getCellData("PatientPut",4,0));
	CreateputNeg.setLastName(er.getCellData("PatientPut",4,1));
	CreateputNeg.setContactNumber(er.getCellData("PatientPut",4,2));
	CreateputNeg.setEmail(er.getCellData("PatientPut",4,3));
	CreateputNeg.setAllergy(er.getCellData("PatientPut",4,4));
	CreateputNeg.setFoodPreference(er.getCellData("PatientPut",4,5));
	CreateputNeg.setCuisineCategory(er.getCellData("PatientPut",4,6));
	CreateputNeg.setDateOfBirth(er.getCellData("PatientPut",4,7));
	
	return CreateputNeg;
}


}
