import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


public class Receiver {

	private Socket socket;
	private InputStream in;
	public Receiver(Socket socket){
		System.out.println("receive start");
		this.socket = socket;
	}
	public String receive(){
		int n=0;
		byte[] receiveByte = new byte[1024];
		try {
			in = socket.getInputStream();
			n =in.read(receiveByte);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new String(receiveByte,0,n));
		return new String(receiveByte,0,n);
	}
}
