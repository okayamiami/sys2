package bean;

public class ChildAbs {

	private String child_id;
	private String child_name;
	private String parents_id;
	private String class_id;
	private boolean is_attend;
	private String facility_id;
	private boolean abs_is_attend;
	private String absence_date;

	public String getChild_id() {
		return child_id;
	}
	public void setChild_id(String child_id) {
		this.child_id = child_id;
	}
	public String getChild_name() {
		return child_name;
	}
	public void setChild_name(String child_name) {
		this.child_name = child_name;
	}
	public String getParents_id() {
		return parents_id;
	}
	public void setParents_id(String parents_id) {
		this.parents_id = parents_id;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public boolean is_attend() {
		return is_attend;
	}
	public void setIs_attend(boolean is_attend) {
		this.is_attend = is_attend;
	}
	public String getFacility_id() {
		return facility_id;
	}
	public void setFacility_id(String facility_id) {
		this.facility_id = facility_id;
	}
	public boolean Abs_is_attend() {
		return abs_is_attend;
	}
	public void setAbs_is_attend(boolean abs_is_attend) {
		this.abs_is_attend = abs_is_attend;
	}
	public String getAbsence_date() {
		return absence_date;
	}
	public void setAbsence_date(String absence_date) {
		this.absence_date = absence_date;
	}

}