package bean;

public class BusChildInfo {

    private String bus_id;       // バスID
    private String bus_name;     // バス名
    private String child_id;     // 子供ID
    private String child_name;   // 子供名
    private String class_id;   // クラスID
    private String class_name;   // クラス名
    private String facility_id;  // 施設ID

    // ゲッターとセッター
    public String getBus_id() {
        return bus_id;
    }
    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }
    public String getBus_name() {
        return bus_name;
    }
    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }
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
    public String getClass_id() {
        return class_id;
    }
    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }
    public String getClass_name() {
        return class_name;
    }
    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
    public String getFacility_id() {
        return facility_id;
    }
    public void setFacility_id(String facility_id) {
        this.facility_id = facility_id;
    }
}
