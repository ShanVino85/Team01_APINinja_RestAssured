package api.pojo;

import java.util.Map;

public class PatientGetResponse {
    private String fileId;
    private String fileName;
    private String uploadDate;
    private Map<String, String> morbidConditions;
    private Map<String, Object> vitals;
    private String morbidConditionStr;

    // Getters and Setters
    public String getFileId() {
        return fileId;
    }

	public String getFileName() {
		return fileName;
	}

	public String getUploadDate() {
		return uploadDate;
	}	

	public Map<String, String> getMorbidConditions() {
		return morbidConditions;
	}

	
	public Map<String, Object> getVitals() {
		return vitals;
	}

	public void setVitals(Map<String, Object> vitals) {
		this.vitals = vitals;
	}
	

	public void setMorbidConditionStr(String morbidConditionStr) {
		this.morbidConditionStr = morbidConditionStr;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

    
    }