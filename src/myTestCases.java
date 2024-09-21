import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mysql.cj.xdevapi.Result;

public class myTestCases {
	WebDriver driver = new ChromeDriver();
	Random rand=new Random();
	Random rand2=new Random();
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	@BeforeTest
	public void mysetup() throws SQLException {
		con =DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","SabaaKamel99");
	}
	
	@Test(priority=3)
	public void Getdata() throws SQLException {
		stmt =con.createStatement();
		rs=stmt.executeQuery("select * from customers where customerNumber=504");
		
		while(rs.next()) {
			int CustomerNumbers=rs.getInt("customerNumber");
			String CustomerNames=rs.getString("customerName");
			System.out.println(CustomerNumbers);
			System.out.println(CustomerNames);
			String last=rs.getString("contactLastName");
			String first=rs.getString("contactFirstName");
			
			driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
			WebElement firstInputFAield=driver.findElement(By.id("firstname"));
			WebElement lastInputField=driver.findElement(By.id("lastname"));
			firstInputFAield.sendKeys(first);
			lastInputField.sendKeys(last);
					
			
		}
		
		
		}
		
	@Test(priority=1)
	public void AdddataToTheDataBase() throws SQLException {
		int randomNumber= rand.nextInt(998) * rand2.nextInt(677);
		String queryInsert="insert into customers (customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,addressLine2,city,state,postalCode,country,salesRepEmployeeNumber,creditLimit)VALUES"
				+ "(504, 'Acme Corporation', 'Doe', 'John', '123-456-7890', '123 Main St', NULL, 'Los Angeles', 'CA', '90001', 'USA', 1370, 50000.00)";
		
		  stmt=con.createStatement();
		int rowInserted=  stmt.executeUpdate(queryInsert);
		System.out.print(rowInserted);
		  
	}
	@Test (priority=2)
	public void updateDataBase() throws SQLException {
		String updateData="update customers set contactFirstName='sabaa' where customerNumber=504";
	

		  stmt=con.createStatement();
		int rowInserted=  stmt.executeUpdate(updateData);
		System.out.print(rowInserted);
	}
	
	@Test(priority=4 ,enabled=false)
	public void deleteData() throws SQLException {
		String deleteDb="delete from customers where customerNumber=504";
		int delete=stmt.executeUpdate(deleteDb);
		System.out.print(delete);
	}
	
	}


