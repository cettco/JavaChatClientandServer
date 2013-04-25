import java.io.IOException;
import java.net.Socket;


public class User {

	private Socket socket;
	private String name;
	public User(Socket socket,String name){
		this.socket = socket;
		this.name = name;
	}
	public Socket getSocket(){
		return socket;
	}
	public String getName(){
		return name;
	}
	public void stop() throws IOException{
		socket.close();
	}
}
