
package api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VitalsPojo {
    @JsonProperty("Weight")
    private double weight;
    
    @JsonProperty("Height")
    private double height;
    
    @JsonProperty("Temperature")
    private double temperature;
    
    @JsonProperty("SP")
    private int sp;
    
    @JsonProperty("DP")
    private int dp;

    // Getters and setters
    public double getWeight() {
        return weight;
    }  

    public double getHeight() {
        return height;
    }

    public double getTemperature() {
        return temperature;
    }
 
    public int getSp() {
        return sp;
    }
    public void setSp(int sp) {
        this.sp = sp;
    }
    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }
}