package pageObjects.wordpress.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.admin.AdminUserPageUI;
import utilities.MySQLConnUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminUserPO extends BasePage {

    WebDriver driver;
    public AdminUserPO(WebDriver driver){
        this.driver = driver;
    }


    public int getUserNumberAtUI( ) {
        waitForElementVisible(driver, AdminUserPageUI.TOTAL_ITEM_COUNT);
        String totalItem = getElementText(driver, AdminUserPageUI.TOTAL_ITEM_COUNT); // return 2 items -> split by " " and return [0] = '2'
        return Integer.parseInt(totalItem.split(" ")[0]);
    }


    public int getUserNumberAtDatabase() {
        Connection conn = MySQLConnUtils.getMySQLConnection();
        int count = 0;
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM wp_users";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                ++count;

                System.out.println("------------------------");

            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //Đóng kết nối
                if (conn != null) {
                    conn.close();
                }

                System.out.println("------------Closed Connection------------------");
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        return count;
    }
}
