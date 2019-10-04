import java.sql.SQLException;

import com.ss.lms.presentation.*;

public class Main {

	public static void main(String[] args) 
	{
		System.out.println("DEBUG!");
		
		try {
			Presentation pres = new PresentationAdmin();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
