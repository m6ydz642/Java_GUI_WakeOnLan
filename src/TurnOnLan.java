import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

class TurnOnLan {
	public static final int PORT = 9;    
	static JOptionPane ErrorMessage = new JOptionPane(); // 에러메시지 객체 호출
	
	public void TurnOnLan() {
		GetSet_IP_Mac iplist = new GetSet_IP_Mac();
		
		  
		List<String> SplitIpList = new ArrayList<String> ();
		SplitIpList.add(iplist.getIp() ); // 아이피주소에 맥주소가 같이 있어서 그 다음 배열이 무조건 맥주소임
	    	
	    // String macStr = "D8:D3:85:00:5A:A9";
	        
		 try {
		     String[] ipStr = iplist.getIp().split(" "); // 아이피 부분은 WakeOneLan클래스에서 
		     // split("; ");로 인해 나눠져서 필요없지만 밑에 맥주소는 형식이 맥주소; (공백) 이라서 split해줘야 함
		     
		     // try문 밖에서 널포인터 오류시 예외 처리 안되서 try문 안으로 넣음
		     String[] macStr = iplist.getMac().split(" ");  // 맥주소 마지막에서 세미콜론으로 구분지어진 다음에 공백이 생겨서 공백부터 나눔 
			 // 가져온 주소는 무조건 1가지의 형태 즉 아이피,맥 (0번과 1번) 한가지라 
			 // 별도로 몇번째 아이피인지 다시 안세도 됨
		  /*  ipStr[0] = ipStr[0].replaceAll(" ", "");
		     macStr[1] = macStr[1].replaceAll(" ", "");*/
		     
			 System.out.println("나눈 아이피 : " + ipStr[0]);
			 
			 System.out.println("나눈 맥주소 : " + macStr[1]);
			
	            byte[] macBytes = getMacBytes(macStr[1]);
	            byte[] bytes = new byte[6 + 16 * macBytes.length];
	            for (int i = 0; i < 6; i++) {
	                bytes[i] = (byte) 0xff;
	            }
	            for (int i = 6; i < bytes.length; i += macBytes.length) {
	                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
	            }
	            
	            InetAddress address = InetAddress.getByName(ipStr[0]);
	            System.out.println("들어온 아이피 " + address);
	            System.out.println("들어온 맥 주소 " + macStr[0]);
	            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
	            DatagramSocket socket = new DatagramSocket();
	            socket.send(packet);
	            socket.close();
	            
	            System.out.println("Wake-on-LAN 패킷 전송 ");
	            System.out.println("전송 아이피 : " + ipStr[0]);
	            System.out.println("전송 맥주소 " + macStr[1]);
	            // 프로그램 실행하면서 로딩한 아이피 보여주기
	    	 	// 나중에 어떤 txt 파일을 읽어서 텍스트 필드로 보여주는 걸로 바꿀 예정 
	        }
	        catch (Exception e) {
	            System.out.println("패킷 전송 실패 맥주소나 아이피 주소 확인 바람 : " + e);
	          // System.exit(1); // 강제 종료말고 alert창 띄울 수 있으면 alert로 할 예정
	          
	            ErrorMessage.showMessageDialog(null, "패킷 전송 실패 맥주소나 아이피 주소 확인 바람");
	        }
	}
	
	private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
		byte[] bytes = new byte[6];
		String[] hex = macStr.split("(\\:|\\-)");
		if (hex.length != 6) {
			throw new IllegalArgumentException("Invalid MAC address.");
		}
		try {
			for (int i = 0; i < 6; i++) {
				bytes[i] = (byte) Integer.parseInt(hex[i], 16);
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("맥 주소가 이상하다 휴먼");
			
		}catch (Exception e) {
			System.out.println("예외 발생 " + e);
			 ErrorMessage.showMessageDialog(null, "맥 주소가 이상하다 휴먼");
		}
		return bytes;
	}
}