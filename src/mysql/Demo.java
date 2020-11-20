package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Demo {

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/labstu?useSSL=false&serverTimezone=GMT%2B8";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "12345678";

    public static void main(String[] args) {
        Connection conn = null;
        Statement state = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("Connect to DB...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println("Instant Statement...");
            state = conn.createStatement();

            String sql;
            sql = "select * from student where(ssex = '男');";

            ResultSet res = state.executeQuery(sql);
            System.out.println("\nResult:");
            List<Student> list = new ArrayList<Student>();
            while (res.next()) {
                Student stu = new Student();
                stu.setSid(res.getString("Sid"));
                stu.setSage(res.getInt("sage"));
                stu.setDid(res.getString("did"));
                stu.setSclass(res.getString("sclass"));
                stu.setSsex(res.getString("ssex"));
                stu.setSname(res.getString("sname"));
                list.add(stu);
                System.out.println(stu.toString());
            }
            System.out.println(list.size());
            res.close();
            state.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
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
    }
}
