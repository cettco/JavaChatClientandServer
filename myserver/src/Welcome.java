
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class Welcome implements Runnable{

	private String udpport;
	private InputStream in;
	private OutputStream out;
	private ServerSocket serverSocket;
	private Jabber jabber;
	private SQL sql;
	public Welcome(String tcpport,String udpport) throws IOException{
		this.udpport = udpport;
		this.serverSocket = new ServerSocket(Integer.parseInt(tcpport));
		jabber = new Jabber(Integer.parseInt(udpport));
		new Thread(jabber).start();
		sql = new SQL();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!serverSocket.isClosed()){
				Socket socket = serverSocket.accept();
				in = socket.getInputStream();
				byte[] b = new byte[1024];
				int n = in.read(b);
				String msg = new String(b,0,n);
				if(msg.substring(0, 2).equals("@2"))
					registerCheck(msg.substring(2),socket);
				else
					loginCheck(msg.substring(2),socket);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void quit(){
		try {
			jabber.quit();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void registerCheck(String message,Socket socket) throws IOException{
		int n = message.indexOf(" ");
		String name = message.substring(0,n);
		String password = message.substring(n+1);
		if(sql.checkname(name)){
			out = socket.getOutputStream();
			String sendmsg = "102";
			out.write(sendmsg.getBytes());
		}
		else{
			sql.insertSql(name, password);
			out = socket.getOutputStream();
			String sendmsg = "101";
			out.write(sendmsg.getBytes());
		}
		out = socket.getOutputStream();
		String sendmsg = "Welcome "+udpport;
		out.write(sendmsg.getBytes());
		User user = new User(socket, name);
		jabber.add(user);
	}
	private void loginCheck(String message,Socket socket) throws IOException{
		int n = message.indexOf(" ");
		String name = message.substring(0,n);
		String password = message.substring(n+1);
		if(sql.check(name, password)){
			String sendmsg = "Welcome "+udpport;
			out.write(sendmsg.getBytes());
			User user = new User(socket, name);
			jabber.add(user);
		}
		else{
			String sendmsg = "102";
			out.write(sendmsg.getBytes());
		}
		socket.close();
			
	}
}
