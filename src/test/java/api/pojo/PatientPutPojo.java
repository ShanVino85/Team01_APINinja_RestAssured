package api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class PatientPutPojo {
	
	@JsonProperty("Weight")
    private float Weight;
	@JsonProperty("Height")
    private float Height;
	@JsonProperty("Temperature")
    private float Temperature;
	@JsonProperty("SP")
    private int SP;
	@JsonProperty("DP")
    private int DP;
	
	
	//added for JSON order
	@JsonPropertyOrder({
		"Weight",
	    "Height",
	    "Temperature",
	    "SP",
	    "DP"
	    })

     public void setWeight(float weight) {
        this.Weight = weight;
    }

      public void setHeight(float height) {
        this.Height = height;
     }
      
      public void setTemperature(float temperature) {
          this.Temperature = temperature;
       }
      public void setSP(int sp) {
          this.SP = sp;
       }
      public void setDP(int dp) {
          this.DP = dp;
       }
      
     
}