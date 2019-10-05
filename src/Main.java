import java.sql.SQLException;

//import com.mysql.jdbc.Connection;
import com.ss.lms.dataaccess.BorrowerDataAccess;
import com.ss.lms.presentation.Presentation;
import com.ss.lms.presentation.PresentationBorrower;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

	public static void main(String args[]) {

		try {
			Presentation pres = new PresentationBorrower();
		}
		catch(Exception e){
		
		}
	}
}