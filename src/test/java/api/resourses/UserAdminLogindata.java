package api.resourses;

import java.io.IOException;
//import java.util.List;
//import java.util.Map;

import api.pojo.UserAdminLoginPojo;
import api.utils.ExcelReader;
//import utilities.PropertyFileReader;

public class UserAdminLogindata {
	
 static ExcelReader er = new ExcelReader();

 public static  UserAdminLoginPojo dataBuild() throws IOException {
	
	 UserAdminLoginPojo dl = new UserAdminLoginPojo();
	
	dl.setPassword(er.getCellData("AdminLogin", 1, 0));
	dl.setUserLoginEmail(er.getCellData("AdminLogin", 1, 1));
	
	return dl;
}
	
 public static  UserAdminLoginPojo invaliddataBuild() throws IOException {
		
	 UserAdminLoginPojo d2 = new UserAdminLoginPojo();
	
	d2.setPassword(er.getCellData("AdminLogin", 2, 0));
	d2.setUserLoginEmail(er.getCellData("AdminLogin", 2, 1));
	
	return d2;
}
 
 
	
	

}
