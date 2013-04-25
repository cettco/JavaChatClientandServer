import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//import com.mysql.jdbc.PreparedStatement;

public class SQL{
		String sql="select * from test3";
		Connection connection;
		ResultSet rs = null;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/instancemessage";
		String user = "root";
		String pwd = "zsq001";
		public SQL(){
			
		}
		public void connSql(){
			try {
				Class.forName(driver);
				connection = (Connection)DriverManager.getConnection(url, user, pwd);
				if(!connection.isClosed()){
					System.out.println("succeed");
				}
				else System.out.println("failed");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		public void selectSql(){
			try {
				
				Statement statement = connection.createStatement();
				rs = statement.executeQuery(sql);
				while(rs.next()){
					System.out.println(rs.getString("name")+rs.getString("pwd"));
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		public void insertSql(String name,String password){
			
			try {
				
				String insql = "insert into test3 (name,pwd) values (?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(insql);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, password);
				int i = preparedStatement.executeUpdate();
				//System.out.println("first");
				if(i>0)System.out.println("insert success");
				//System.out.println("hello world");
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		public boolean check(String name,String password){
			boolean findit = false;
			try {
				//String search = "select"
				
				Statement statement = connection.createStatement();
				rs = statement.executeQuery(sql);
				//System.out.println("first");
				while(rs.next()){
					//System.out.println("first");
					String username = rs.getString("name");
					String userpwd = rs.getString("pwd");
					//System.out.println(username+" "+userpwd);
					if(name.equals(username)&&password.equals(userpwd))
						{
						System.out.println("find");
						findit = true;
						break;
						}
						
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("error");
			}
			//System.out.println("fail find");
			return findit;
		}
		
		public boolean checkname(String name){
			boolean findit = false;
			try {
				//String search = "select"
				
				Statement statement = connection.createStatement();
				rs = statement.executeQuery(sql);
				//System.out.println("first");
				while(rs.next()){
					//System.out.println("first");
					String username = rs.getString("name");
					//System.out.println(username+" "+userpwd);
					if(name.equals(username))
						{
						System.out.println("find");
						findit = true;
						break;
						}
						
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("error");
			}
			//System.out.println("fail find");
			return findit;
		}
	}