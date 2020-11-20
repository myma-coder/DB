package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExeQuery {

    // JDBC �����������ݿ� URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/labstu?useSSL=false&serverTimezone=GMT%2B8";

    // ���ݿ���û��������룬��Ҫ�����Լ�������
    static final String USER = "root";
    static final String PASS = "12345678";

    private Connection conn = null;
    private Statement state = null;

    public ExeQuery() {
        try {
            // ע�� JDBC ����
            Class.forName(JDBC_DRIVER);

            // ������
            System.out.println("Connect to DB...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // ִ�в�ѯ
            System.out.println("Instant Statement...");
            state = conn.createStatement();

        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public ObservableList<Student> exeQuery(String sql) {
        ObservableList<Student> list = FXCollections.observableArrayList();
        try {
            ResultSet res = state.executeQuery(sql);
            while (res.next()) {
                Student stu = new Student();
                stu.setSid(res.getString("sid"));
                stu.setSage(res.getInt("sage"));
                stu.setDid(res.getString("did"));
                stu.setSclass(res.getString("sclass"));
                stu.setSsex(res.getString("ssex"));
                stu.setSname(res.getString("sname"));
                list.add(stu);
                System.out.println(stu.toString());
            }
            res.close();
            state.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (state != null) {
                    state.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
