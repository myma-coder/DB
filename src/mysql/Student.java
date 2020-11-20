package mysql;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {

    private StringProperty sid;
    private StringProperty sname;
    private StringProperty ssex;
    private IntegerProperty sage;
    private StringProperty did;
    private StringProperty sclass;


    public Student() {
        super();
        this.sid = new SimpleStringProperty();
        this.sname = new SimpleStringProperty();
        this.ssex = new SimpleStringProperty();
        this.did = new SimpleStringProperty();
        this.sclass = new SimpleStringProperty();
        this.sage = new SimpleIntegerProperty();
    }

    public StringProperty sid() {
        return sid;
    }

    public String getSid() {
        return sid.get();
    }

    public void setSid(String sid) {
        this.sid.set(sid);
    }

    public StringProperty sname() {
        return sname;
    }

    public String getSname() {
        return sname.get();
    }

    public void setSname(String sname) {
        this.sname.set(sname);
    }

    public StringProperty sclass() {
        return sclass;
    }

    public String getSclass() {
        return sclass.get();
    }

    public void setSclass(String sclass) {
        this.sclass.set(sclass);
    }

    public StringProperty did() {
        return did;
    }

    public String getDid() {
        return did.get();
    }

    public void setDid(String did) {
        this.did.set(did);
    }
    public StringProperty ssex() {
        return ssex;
    }

    public String getSsex() {
        return ssex.get();
    }

    public void setSsex(String ssex) {
        this.ssex.set(ssex);
    }

    public IntegerProperty sage() {
        return sage;
    }

    public Integer getSage() {
        return sage.get();
    }

    public void setSage(Integer sage) {
        this.sage.set(sage);
    }

    @Override
    public String toString() {
        return getSid() + " " + getSname() + " "+ getSsex() + " " + getSage() + " " + getDid() + " " + getSclass();
    }
}
