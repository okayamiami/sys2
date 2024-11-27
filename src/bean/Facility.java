package bean;

public class Facility {

	private String facility_id;
	private String facility_name;
	private String facility_address;
	private String facility_tel;
	private String facility_mail;
	private String facility_app_password;
	private Boolean facility_plan;

	public String getFacility_id() {
		return facility_id;
	}
	public void setFacility_id(String facility_id) {
		this.facility_id = facility_id;
	}
	public String getFacility_name() {
		return facility_name;
	}
	public void setFacility_name(String facility_name) {
		this.facility_name = facility_name;
	}
	public String getFacility_address() {
		return facility_address;
	}
	public void setFacility_address(String facility_address) {
		this.facility_address = facility_address;
	}
	public String getFacility_app_password() {
        return facility_app_password;
    }
	public void setFacility_app_password(String facility_app_password) {
        this.facility_app_password = facility_app_password;
    }
	public String getFacility_tel() {
		return facility_tel;
	}
	public void setFacility_tel(String facility_tel) {
		this.facility_tel = facility_tel;
	}
	public String getFacility_mail() {
		return facility_mail;
	}
	public void setFacility_mail(String facility_mail) {
		this.facility_mail = facility_mail;
	}
	public Boolean getFacility_plan() {
		return facility_plan;
	}
	public void setFacility_plan(Boolean facility_plan) {
		this.facility_plan = facility_plan;
	}


}