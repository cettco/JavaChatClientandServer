import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Connection {
	private Socket socket;
	private String serverAddress;
	private int serverTcpPort;
	private InputStream in;
	private OutputStream out;
	private String userName;
	private String password;
	private int port;
	public Connection(String userName,String password,String serverAddress,String serverTcpPort){
		this.serverAddress = serverAddress;
		this.serverTcpPort = Integer.parseInt(serverTcpPort);
		this.userName = userName;
		this.password = password;
	}
	public int connect(){
		try {
			socket = new Socket(this.serverAddress,this.serverTcpPort);
			out = socket.getOutputStream();
			String msg = "@1"+userName+" "+password;
			out.write(msg.getBytes());
			byte[] b =new byte[1024];
			System.out.println("read");
			in = socket.getInputStream();
			int n = in.read(b);
			System.out.println("read2");
			String result = new String(b,0,n);
			System.out.println(result);
			String re = result.substring(0,3);
			if(re.equals("101")){
				port = Integer.parseInt(result.substring(3));
				return 101;
			}
			else return 102;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("Wrong Host name");
			return 103;
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Wrong IO");
			return 103;
			//e.printStackTrace();
		}
	}
	public Socket getSocket(){
		return socket;
	}
	public int getPort(){
		return port;
	}
}
