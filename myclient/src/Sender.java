import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Sender {

	private DatagramSocket udpSocket;
	private int port;
	private String serverAddress;
	public Sender(DatagramSocket udpSocket,int port,String serverAddress){
		this.udpSocket = udpSocket;
		this.port = port;
		this.serverAddress = serverAddress;
	}
	public void send(String msg){
		try {
			byte[] buf = msg.getBytes();
			InetAddress address = InetAddress.getByName(serverAddress);
			DatagramPacket packet = new DatagramPacket(buf, buf.length,address,port);
			udpSocket.send(packet);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
