package busBooking;
import java.sql.*;

public class connectDB {

	public Connection getConnection()
	{
		Connection connection=null;
		try
		{
			String dbURL = "jdbc:sqlserver://localhost;user=sa;password=12345;databaseName=bus_management";
			String user = "sa";
			String pass = "12345";
			connection  = DriverManager.getConnection(dbURL, user, pass);
		}
		catch(SQLException sqlException) 
		{
			sqlException.printStackTrace();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return connection;
	}
}
