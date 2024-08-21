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

public class DietitianPostPojo {

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
	    "Integer id" 
	})

	
	@JsonProperty("ContactNumber")
	private String contact_Number;
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
	@JsonProperty("Integer id")
	private String Integer_id;
	@JsonProperty("DateOfBirth")
	private String date_Of_Birth;
	@JsonProperty("Education")
	private String education;
	
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
	public String getInteger_id() {
		return Integer_id;
	}
	public void setInteger_id(String integer_id) {
		Integer_id = integer_id;
	}
	

}

	
	/*private String ContactNumber;
	private String DateOfBirth;
	private String Education;
	private String Email;
	private String Firstname;
	private String HospitalCity;
	private String HospitalName;
	private String HospitalPincode;
	private String HospitalStreet;
	private String Lastname;
	
	public String getContactNumber() {
		return ContactNumber;
	}

	public void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public String getEducation() {
		return Education;
	}
	public void setEducation(String education) {
		Education = education;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getHospitalCity() {
		return HospitalCity;
	}
	public void setHospitalCity(String hospitalCity) {
		HospitalCity = hospitalCity;
	}
	public String getHospitalName() {
		return HospitalName;
	}
	public void setHospitalName(String hospitalName) {
		HospitalName = hospitalName;
	}
	public String getHospitalPincode() {
		return HospitalPincode;
	}
	public void setHospitalPincode(String hospitalPincode) {
		HospitalPincode = hospitalPincode;
	}
	public String getHospitalStreet() {
		return HospitalStreet;
	}
	public void setHospitalStreet(String hospitalStreet) {
		HospitalStreet = hospitalStreet;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	
	*/

