import com.kuang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyTest {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
             conn = JdbcUtils.getConnection(); //获取数据库连接
             st = conn.createStatement(); //获得sql的执行对象
             String sql = "insert into users(id, `NAME`, `PASSWORD`, `email`, `birthday`)" +
                     "values(4, 'xiheng', '123456', 'wxh604328815@gmail.com', '2020-01-01')";

            int i = st.executeUpdate(sql);
            if(i > 0){
                System.out.println("插入成功");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
