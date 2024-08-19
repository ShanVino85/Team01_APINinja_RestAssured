package api.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MorbidityDetailsPojo {
    @JsonProperty("T3")
    private String t3;
    
    @JsonProperty("T4")
    private String t4;
    
    @JsonProperty("TSH")
    private String tsh;

    // Getters and setters
    public String getT3() {
        return t3;
    }

    

    public String getT4() {
        return t4;
    }

   

    public String getTSH() {
        return tsh; // Fixed to use the correct field name
    }

}