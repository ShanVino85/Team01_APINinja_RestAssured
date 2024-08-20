package api.resourses;

import java.io.IOException;

import api.pojo.PatientPostPojo;
import api.pojo.PatientPutPojo;
import api.utils.ExcelReader;

public class PatientPutVitalsdata {
	
	static ExcelReader er = new ExcelReader();

	
	
public static  PatientPutPojo AddPdfAddvitalsPUT() throws IOException  {
		
		PatientPutPojo Createpatientput1 = new PatientPutPojo();
		
        
		Createpatientput1.setWeight(Float.parseFloat(er.getCellData("Patientputvital", 1, 0)));
		Createpatientput1.setHeight(Float.parseFloat(er.getCellData("Patientputvital", 1, 1)));
		Createpatientput1.setTemperature(Float.parseFloat(er.getCellData("Patientputvital", 1, 2)));
		Createpatientput1.setSP(Integer.parseInt(er.getCellData("Patientputvital", 1, 3)));
		Createpatientput1.setDP(Integer.parseInt(er.getCellData("Patientputvital", 1, 4)));
		
		return Createpatientput1;
	}

}
