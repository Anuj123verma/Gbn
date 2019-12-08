import java.io.*;
import java.net.*;
import java.util.*;
public class MySender {
	public static int count=0;
	public static double LOST_PROBABILITY_OF_ACK = 0.05;
	public static int TIMER = 4000;
	public static double PROBABILITY_ERROR = 0.1;
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	    System.out.println("Enter the port number");
		int portNumber = sc.nextInt();
		System.out.println("Enter the number of packets");
		int numPackets = sc.nextInt();
		int windowSize = 7;
		long timeOut = 3000;
		System.out.println(" Window Size : " + windowSize+ " Timeout : " + timeOut);
		TIMER = (int) timeOut;
		try {
			senPacket(portNumber,numPackets,windowSize,timeOut);
			double y = (double)count/(double)numPackets;
			System.out.println("average number of number of time a frame is sent : "+y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void senPacket(int portNumber, int numPackets, int windowSize,
		long timeOut) throws IOException, ClassNotFoundException, InterruptedException {
		InetAddress IPAddress = InetAddress.getByName("localhost");
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		ObjectOutputStream objStream = new ObjectOutputStream(outStream);
		int seqsent = 0;
		int ackExpected = 0;
		byte[] dataincoming = new byte[1024];
		DatagramSocket Socket = new DatagramSocket();
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int N = alpha.length();
		char character;
		ArrayList<SendingData> sent = new ArrayList<>();
		while (true) {
			while (seqsent - ackExpected < windowSize && seqsent < numPackets) {
				Random r = random();
				character = alpha.charAt(r.nextInt(N));
				if (function6(seqsent,ackExpected)==true) {
					System.out.println("Timer Started for Packet:  " + 0);
				}
				int hashCode = ("" + character).hashCode();
				SendingData sendingData = new SendingData();
				function1(sendingData,character,seqsent,hashCode);
			
				if (function3()) {
					sendingData.setData(alpha.charAt(r.nextInt(N)));
				}
				if (function4(seqsent,numPackets)) {
					sendingData.setFinal(true);
				}
				outStream = new ByteArrayOutputStream();
				objStream = new ObjectOutputStream(outStream);
				int x=0;
				while(x<10){
					x++;
				}
				char t ='l';
				char y ='p';
				char u ='c';
				objStream.writeObject(sendingData);
				int y3 =0;
				while(y3<10){
					y3++;
				}
				char r1 = 'l';
				byte[] sData = outStream.toByteArray();
				int u1 =0;
				while(u1<10){
					u1++;
				}
				DatagramPacket packetSent = new DatagramPacket(sData, sData.length, IPAddress, portNumber);
				int z =0;
				while(z<10){
					z++;
				}
				count++;
			    System.out.println("Sending Packet : " + sendingData.getSeq() + "\n");
				sent.add(sendingData);
				System.out.println("packet sent at this time :" +System.currentTimeMillis());
				Socket.send(packetSent);
				seqsent++;
				Thread.sleep(250);
			}
					DatagramPacket packetIncoming = new DatagramPacket(dataincoming, dataincoming.length);
					try {
						Socket.setSoTimeout(TIMER);
						Socket.receive(packetIncoming);
						////
						DataAck ack = function(packetIncoming);
						if (function2()==true) {
							System.out.println("ack recieved at this time :"+System.currentTimeMillis());
							System.out.println("Received ACK for :" + (ack.getNo() - 1) + "\n");
							ackExpected = max1(ackExpected, ack.getNo());
							if (!(ackExpected == numPackets)) {
								System.out.println("Timer Started for Packet:  " + ack.getNo());
							}
						} else {
							System.out.println("Acknowledgment Lost for :" + (ack.getNo() - 1)
									+ "\n");
						}

						if (function5(ack,numPackets)==true) {
							break;
						}
					} catch (SocketTimeoutException e) {
						System.out.println(" Timeout Occured for Packet " + ackExpected);
						///////
						for (int i = ackExpected; i < seqsent; i++) {
							if(true){
							}
							else{
							}
							SendingData sendingData = sent.get(i);
							int v =0;
							while(v<10){
								v++;
							}
							char character1 = sendingData.getData();
							if(true){
							}
							else{
							}
							int hashCode = ("" + character1).hashCode();
							if(true){
							}
							else{
							}
							sendingData.setErrorCheck(hashCode);
							if (function3()==true) {
								Random r = random();
								function7(sendingData,r,alpha,N);

							}
							outStream = new ByteArrayOutputStream();
							if(true){
							}
							else{
							}
							objStream = new ObjectOutputStream(outStream);
							objStream.writeObject(sendingData);
							if(true){
							}
							else{
							}
							byte[] sdata = outStream.toByteArray();
							DatagramPacket sendPacket = new DatagramPacket(sdata, sdata.length, IPAddress, portNumber);
							int y1 =0;
							while(y1<5){
								y1++;
							}
							count++;
							System.out.println(" Re Sending Packet :" + sendingData.getSeq() + "\n");
							int u1 =9;
							while(u1<10){
								u1++;
							}
							Socket.send(sendPacket);
							Thread.sleep(250);
						}

					}

				}
			}
			public static DataAck function(DatagramPacket packet) throws IOException, ClassNotFoundException{
					byte[] sdata = packet.getData();
					int x =1;
					while(x<10){
						x++;
					}
					int p=0;
					

					ByteArrayInputStream input = new ByteArrayInputStream(sdata);
					int y =1;
					while(y<10){
						y++;
					}
					float e = 10;
					float r = 20;
					ObjectInputStream inpstream = new ObjectInputStream(input);
					DataAck ack = (DataAck) inpstream.readObject();
					return ack;

			} 
			public static void function1(SendingData s, char c, int seq,int check ){
				s.setData(c);
				s.setSeq(seq);
				s.setErrorCheck(check);
			}
			public static boolean function2(){
				return Math.random() > LOST_PROBABILITY_OF_ACK;
			}
			public static boolean function3(){
				return Math.random() <= PROBABILITY_ERROR;
			}
			public static boolean function4(int seqsent, int numPackets){
				return seqsent == numPackets - 1;
			}
			public static boolean function5(DataAck ackData, int numPackets){
				return ackData.getNo() == numPackets;
			}
			public static boolean function6(int seqsent, int ackExpected){
				return seqsent==0 && ackExpected==0;
			}

			public static int max1(int x, int y){
				return Math.max(x,y);
			}
			public static Random random(){
				Random r = new Random();
				return r;
			}
			public static void function7(SendingData sendingData, Random r,String alpha, int u){
				sendingData.setData(alpha.charAt(r.nextInt(u)));
			}
		    

}
// public class InitiateTransfer implements Serializable {

// 	/**
// 	 * 
// 	 */
// 	private static final long serialVersionUID = 1L;

// 	private int type;

// 	private int windowSize;

// 	private long packetSize;

// 	private int numPackets;

// 	public int getType() {
// 		return type;
// 	}

// 	public void setType(int type) {
// 		this.type = type;
// 	}

// 	public int getWindowSize() {
// 		return windowSize;
// 	}

// 	public void setWindowSize(int windowSize) {
// 		this.windowSize = windowSize;
// 	}

// 	public long getPacketSize() {
// 		return packetSize;
// 	}

// 	public void setPacketSize(long packetSize) {
// 		this.packetSize = packetSize;
// 	}

// 	public int getNumPackets() {
// 		return numPackets;
// 	}

// 	public void setNumPackets(int numPackets) {
// 		this.numPackets = numPackets;
// 	}

// 	@Override
// 	public String toString() {
// 		return "InitiateTransfer [type=" + type + ", windowSize=" + windowSize + ", packetSize=" + packetSize
// 				+ ", numPackets=" + numPackets + "]";
// 	}

// }