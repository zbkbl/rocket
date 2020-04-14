package flinkDemo;

import flinkDemo.po.Student;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-08-07 15:43
 **/
public class SourceFromMySQL extends RichSourceFunction<Student> {

    PreparedStatement ps;
    private Connection connection;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = getConnection();
        String sql = "select * from student;";
        ps = this.connection.prepareStatement(sql);
    }

    @Override
    public void close() throws Exception {
        super.close();
        if (connection != null) {
            connection.close();
        }
        if (ps != null) {
            ps.close();
        }
    }

    @Override
    public void run(SourceContext<Student> sourceContext) throws Exception {
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Student student = new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("name").trim(),
                    resultSet.getString("password").trim(),
                    resultSet.getInt("age")
            );
            sourceContext.collect(student);
        }
    }

    @Override
    public void cancel() {

    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rcd_pixiu?useUnicode=true&characterEncoding=UTF-8", "root", "3385458");
        } catch (Exception e) {
            System.out.println("--------mysql get connection has exception , msg = " + e.getMessage());
        }

        return connection;
    }
}
