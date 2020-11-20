package javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mysql.ExeQuery;
import mysql.Student;

public class FXMLController implements Initializable{

    @FXML
    private CheckBox cb_sid, cb_sname, cb_sclass, cb_sage, cb_did, cb_ssex, cb_ssex_f, cb_ssex_m;

    @FXML
    private TextField tf_sid, tf_sname, tf_sclass, tf_did, tf_sage_from, tf_sage_to;

    @FXML
    private Button bt_search;

    @FXML
    private TextArea ta_sql;

    @FXML
    private TableView<Student> tv_stu;
    @FXML
    private TableColumn<Student, String> tc_sid, tc_sname, tc_did, tc_sclass, tc_ssex;
    @FXML
    private TableColumn<Student, Integer> tc_sage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        tc_sid.setCellValueFactory(CellData -> CellData.getValue().sid());
        tc_sclass.setCellValueFactory(CellData -> CellData.getValue().sclass());
        tc_ssex.setCellValueFactory(CellData -> CellData.getValue().ssex());
        tc_did.setCellValueFactory(CellData -> CellData.getValue().did());
        tc_sname.setCellValueFactory(CellData -> CellData.getValue().sname());
        tc_sage.setCellValueFactory(CellData -> CellData.getValue().sage().asObject());
    }

    /**btn search
     * @param event
     */
    public void search(ActionEvent event) {
        String sql = getSql();
        ta_sql.setText(sql);
        System.err.println("==>sql: " +sql);
        ObservableList<Student> list = new ExeQuery().exeQuery(sql);
        tv_stu.setItems(list);
    }


    /**get sql
     * @return
     */
    private String getSql() {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from student where");
        int firstflag = 0;
        //sid
        if (cb_sid.isSelected() && !tf_sid.getText().trim().isEmpty()) {
            sb.append("(sid like '"+tf_sid.getText().trim()+ "')");
            firstflag = 1;
        }
        //sclass
        if (cb_sclass.isSelected() && !tf_sclass.getText().trim().isEmpty()) {
            if (firstflag == 1) {
                sb.append(" and (sclass like '"+tf_sclass.getText().trim()+ "')");
            } else {
                sb.append("(sclass like '"+tf_sclass.getText().trim()+ "')");
                firstflag = 1;
            }
        }
        //sname
        if (cb_sname.isSelected() && !tf_sname.getText().trim().isEmpty()) {
            if (firstflag == 1) {
                sb.append(" and (sname like '"+tf_sname.getText().trim()+ "')");
            } else {
                sb.append("(sname like '"+tf_sname.getText().trim()+ "')");
                firstflag = 1;
            }
        }

        //did
        if (cb_did.isSelected() && !tf_did.getText().trim().isEmpty()) {
            if (firstflag == 1) {
                sb.append(" and (did like '"+tf_did.getText().trim()+ "')");
            } else {
                sb.append("(did like '"+tf_did.getText().trim()+ "')");
                firstflag = 1;
            }
        }
        //sage
        if (cb_sage.isSelected() && !tf_sage_from.getText().trim().isEmpty()) {
            int agef = Integer.valueOf(tf_sage_from.getText().trim());
            if (firstflag == 1) {
                sb.append(" and (sage >= "+agef+ ")");
                if (!tf_sage_to.getText().trim().isEmpty()) {
                    int aget = Integer.valueOf(tf_sage_to.getText().trim());
                    if (aget > agef) {
                        sb.append(" and (sage <= "+aget+ ""
                                + ")");
                    }
                }

            } else {
                sb.append(" (sage >= "+agef+ ")");
                if (!tf_sage_to.getText().trim().isEmpty()) {
                    int aget = Integer.valueOf(tf_sage_to.getText().trim());
                    if (aget > agef) {
                        sb.append(" and (sage <= "+aget+ ")");
                    }
                }
                firstflag = 1;
            }
        }
        //ssex
        if (cb_ssex.isSelected()) {
            String sex;
            if (cb_ssex_m.isSelected()) {
                sex = "ÄÐ";
            } else if (cb_ssex_f.isSelected()) {
                sex = "Å®";
            } else {
                sex = "";
            }
            if (!sex.equals("")) {
                if (firstflag == 1) {
                    sb.append(" and (ssex = '"+ sex + "')");
                } else {
                    sb.append("(ssex = '"+ sex + "')");
                    firstflag = 1;
                }
            }
        }
        sb.append(";");
        return sb.toString();
    }

    /**
     * ³õÊ¼»¯
     */
    private void init() {
        cb_ssex.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    cb_ssex_m.setSelected(true);
                } else {
                    cb_ssex_f.setSelected(false);
                    cb_ssex_m.setSelected(false);
                }

            }
        });
        cb_ssex_f.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (cb_ssex_f.isSelected()) {
                    cb_ssex_m.setSelected(false);
                }
            }

        });
        cb_ssex_m.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (cb_ssex_m.isSelected()) {
                    cb_ssex_f.setSelected(false);
                }
            }

        });
    }
}
