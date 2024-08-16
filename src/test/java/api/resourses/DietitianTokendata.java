package api.resourses;

import java.io.IOException;

import api.pojo.DietitianPostPojo;
import api.pojo.UserAdminLoginPojo;
import api.utils.ExcelReader;
import api.utils.IdHolder;

public class DietitianTokendata {
	

 public static  UserAdminLoginPojo dataTokenBuild() throws IOException {
	
	 UserAdminLoginPojo dl = new UserAdminLoginPojo();
		
		dl.setPassword(IdHolder.dietitianpass);
		dl.setUserLoginEmail(IdHolder.dietitianEmail);
		
		return dl;
}
 
}
