package api.resourses;

import java.io.IOException;

import api.pojo.UserAdminLoginPojo;
import api.utils.ExcelReader;

public class UserAdminLogindata {
	
 static ExcelReader er = new ExcelReader();

 public static  UserAdminLoginPojo dataBuild() throws IOException {
	
	 UserAdminLoginPojo dl = new UserAdminLoginPojo();
	
	dl.setPassword(er.getCellData("AdminLogin", 1, 0));
	dl.setUserLoginEmail(er.getCellData("AdminLogin", 1, 1));
	
	return dl;
}
 
 
	
	

}
