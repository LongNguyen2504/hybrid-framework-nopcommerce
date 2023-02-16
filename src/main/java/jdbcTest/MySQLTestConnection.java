package jdbcTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTestConnection {
    public static Connection getMyConnection() throws SQLException,ClassNotFoundException {
        return MySQLConnUtils.getMySQLConnection();
    }

    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        System.out.println("Get connection...");

        //Lấy ra đối tượng Connection kết nối vào database
        Connection conn = MySQLTestConnection.getMyConnection();

        System.out.println("Opened connection: " + conn);
        Statement statement = conn.createStatement();
        /*String sql = "Select Emp.Emp_Id, Emp.First_Name, Emp.Last_Name, Emp.Dept_Id From Employee Emp;";*/
        String sql = "Select * From BRANCH ;";

        String insertValue = "INSERT INTO BRANCH (ADDRESS,CITY,NAME,STATE,ZIP_CODE) VALUES ('1 Le Dai Hanh', 'Tay Bac','Volvo', 'NNNN', '50000')";
        int rowCount = statement.executeUpdate(insertValue); // statement.executeUpdate truyền vào câu lệnh insert,update,delete... và executeUpdate trả về kiểu int là kết quả số row đã affected
        System.out.println(rowCount + "have been affected");

        //Execute Sql querry return Object ResultSet
        ResultSet rs = statement.executeQuery(sql); // truyền vào câu lệnh querry có trả về data như SELECT

        //Duyệt trên kết quả trả về
       /* while (rs.next()) {
            //Di chuyển con trỏ xuống bản ghi kế tiếp
            int empId = rs.getInt(1);
            String empFirstName = rs.getString(2);
            String empLastName = rs.getString("Last_Name");

            System.out.println("------------------------");
            System.out.println("Emp Id:" + empId);
            System.out.println("Emp FirstName : " + empFirstName);
            System.out.println("Emp LastName : " + empLastName);
        }*/

        //Duyệt trên kết quả trả về
        while (rs.next()) {
            //Di chuyển con trỏ xuống bản ghi kế tiếp
            int branchID = rs.getInt(1); // 1 là index của cột cần lấy (đánh dấu từ 1..)
            String address = rs.getString("ADDRESS"); // truyền vào tên cột "ADDRESS" hoặc index cột đều dc
            String city = rs.getString("CITY");
            String name = rs.getString("NAME");
            String state = rs.getString("STATE");
            String zipcode = rs.getString("ZIP_CODE");

            System.out.println("------------------------");
            System.out.println("Branch Id:" + branchID);
            System.out.println("address : " + address);
            System.out.println("city : " + city);
            System.out.println("name : " + name);
            System.out.println("state : " + state);
            System.out.println("zipcode : " + zipcode);
        }
        //Đóng kết nối
        conn.close();
        System.out.println("------------Closed Connection------------------");
    }
}
