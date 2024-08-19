package api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserDieticianPostPojo {

@JsonInclude(JsonInclude.Include.NON_NULL)
    
@JsonPropertyOrder({
    "ContactNumber",
    "DateOfBirth",
    "Education",
    "Email",
    "Firstname",
    "HospitalCity",
    "HospitalName",
    "HospitalPincode",
    "HospitalStreet",
    "Lastname",
    "Dietician id",
    "loginPassword",
    "put_ContactNumber",
    "put_DateOfBirth",
    "put_Education",
    "put_Email",
    "put_Firstname",
    "put_HospitalCity",
    "put_HospitalName",
    "put_HospitalPincode",
    "put_HospitalStreet",
    "put_Lastname"
})



public String getContact_Number() {
	return contact_Number;
}
public void setContact_Number(String contact_Number) {
	this.contact_Number = contact_Number;
}
public String getDate_Of_Birth() {
	return date_Of_Birth;
}
public void setDate_Of_Birth(String date_Of_Birth) {
	this.date_Of_Birth = date_Of_Birth;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public String getHospital_City() {
	return hospital_City;
}
public void setHospital_City(String hospital_City) {
	this.hospital_City = hospital_City;
}
public String getHospital_Name() {
	return hospital_Name;
}
public void setHospital_Name(String hospital_Name) {
	this.hospital_Name = hospital_Name;
}
public String getHospital_Pincode() {
	return hospital_Pincode;
}
public void setHospital_Pincode(String hospital_Pincode) {
	this.hospital_Pincode = hospital_Pincode;
}
public String getHospital_Street() {
	return hospital_Street;
}
public void setHospital_Street(String hospital_Street) {
	this.hospital_Street = hospital_Street;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}


public String getEducation() {
	return education;
}
public void setEducation(String education) {
	this.education = education;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public String getDietician_Id() {
	return dietician_Id;
}
public void setDietician_Id(String dietician_Id) {
	this.dietician_Id = dietician_Id;
}

public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}

    
@JsonProperty("ContactNumber")	
private String contact_Number;
@JsonProperty("DateOfBirth")
private String date_Of_Birth;
@JsonProperty("Education")
private String education;
@JsonProperty("Email")
private String email;
@JsonProperty("Firstname")
private String first_name;
@JsonProperty("HospitalCity")
private String hospital_City;
@JsonProperty("HospitalName")
private String hospital_Name;
@JsonProperty("HospitalPincode")
private String hospital_Pincode;
@JsonProperty("HospitalStreet")
private String hospital_Street;
@JsonProperty("Lastname")
private String last_name;
@JsonProperty("Dietician id")
private String dietician_Id;
@JsonProperty("loginPassword")
private String pwd;
@JsonProperty("put_ContactNumber")	
private String put_contact_Number;
public String getPut_contact_Number() {
	return put_contact_Number;
}
public void setPut_contact_Number(String put_contact_Number) {
	this.put_contact_Number = put_contact_Number;
}
public String getPut_date_Of_Birth() {
	return put_date_Of_Birth;
}
public void setPut_date_Of_Birth(String put_date_Of_Birth) {
	this.put_date_Of_Birth = put_date_Of_Birth;
}
public String getPut_education() {
	return put_education;
}
public void setPut_education(String put_education) {
	this.put_education = put_education;
}
public String getPut_email() {
	return put_email;
}
public void setPut_email(String put_email) {
	this.put_email = put_email;
}
public String getPut_first_name() {
	return put_first_name;
}
public void setPut_first_name(String put_first_name) {
	this.put_first_name = put_first_name;
}
public String getPut_hospital_City() {
	return put_hospital_City;
}
public void setPut_hospital_City(String put_hospital_City) {
	this.put_hospital_City = put_hospital_City;
}
public String getPut_hospital_Name() {
	return put_hospital_Name;
}
public void setPut_hospital_Name(String put_hospital_Name) {
	this.put_hospital_Name = put_hospital_Name;
}
public String getPut_hospital_Pincode() {
	return put_hospital_Pincode;
}
public void setPut_hospital_Pincode(String put_hospital_Pincode) {
	this.put_hospital_Pincode = put_hospital_Pincode;
}
public String getPut_hospital_Street() {
	return put_hospital_Street;
}
public void setPut_hospital_Street(String put_hospital_Street) {
	this.put_hospital_Street = put_hospital_Street;
}
public String getPut_last_name() {
	return put_last_name;
}
public void setPut_last_name(String put_last_name) {
	this.put_last_name = put_last_name;
}


@JsonProperty("put_DateOfBirth")
private String put_date_Of_Birth;
@JsonProperty("put_Education")
private String put_education;
@JsonProperty("put_Email")
private String put_email;
@JsonProperty("put_Firstname")
private String put_first_name;
@JsonProperty("put_HospitalCity")
private String put_hospital_City;
@JsonProperty("put_HospitalName")
private String put_hospital_Name;
@JsonProperty("put_HospitalPincode")
private String put_hospital_Pincode;
@JsonProperty("put_HospitalStreet")
private String put_hospital_Street;
@JsonProperty("put_Lastname")
private String put_last_name;

}
