package api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


public class PatientPostPojo {
	@JsonProperty("FirstName")
    private String FirstName;
	@JsonProperty("LastName")
    private String LastName;
	@JsonProperty("ContactNumber")
    private String ContactNumber;
	@JsonProperty("Email")
    private String Email;
	@JsonProperty("Allergy")
    private String Allergy;
	@JsonProperty("FoodPreference")
    private String FoodPreference;
	@JsonProperty("CuisineCategory")
    private String CuisineCategory;
	@JsonProperty("DateOfBirth")
    private String DateOfBirth;
	
	//added for JSON order
	@JsonPropertyOrder({
		"FirstName",
	    "LastName",
	    "ContactNumber",
	    "Email",
	    "Allergy",
	    "FoodPreference",
	    "CuisineCategory",
	    "DateOfBirth"
	})

     public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

      public void setLastName(String lastName) {
        this.LastName = lastName;
    }


    public void setContactNumber(String contactNumber) {
        this.ContactNumber = contactNumber;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    public void setAllergy(String allergy) {
        this.Allergy = allergy;
    }
    public void setFoodPreference(String foodPreference) {
        this.FoodPreference = foodPreference;
    }
    public void setCuisineCategory(String cuisineCategory) {
        this.CuisineCategory = cuisineCategory;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.DateOfBirth = dateOfBirth;
    }
}

