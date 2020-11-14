import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/*class IP_MAC extends JTextComponent implements SwingConstants { // text필드 정의
	
}*/
/*****************************************************************/
class IpList extends JButton{ 
	public Component IpList() {
		JButton Start = new JButton("부분 부팅 준비");
		Start.setBounds(626,117,200,30); // 텍스트 필드 
		return Start;
	

	}
}
/*****************************************************************/
class TurnOnLan {
	public static final int PORT = 9;    
	
	public void TurnOnLan() {
		
	     String ipStr = "192.168.0.35"; // 일단은 하드코딩 된 상태로 둠
	     String macStr = "D8-D3-85-00-5A-A9";
	        
		 try {
	            byte[] macBytes = getMacBytes(macStr);
	            byte[] bytes = new byte[6 + 16 * macBytes.length];
	            for (int i = 0; i < 6; i++) {
	                bytes[i] = (byte) 0xff;
	            }
	            for (int i = 6; i < bytes.length; i += macBytes.length) {
	                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
	            }
	            
	            InetAddress address = InetAddress.getByName(ipStr);
	            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
	            DatagramSocket socket = new DatagramSocket();
	            socket.send(packet);
	            socket.close();
	            
	            System.out.println("Wake-on-LAN 패킷 전송 ");
	            System.out.println("전송 아이피 : " + ipStr);
	            // 프로그램 실행하면서 로딩한 아이피 보여주기
	    	 	// 나중에 어떤 txt 파일을 읽어서 텍스트 필드로 보여주는 걸로 바꿀 예정 
	        }
	        catch (Exception e) {
	            System.out.println("패킷 전송 실패 맥주소나 아이피 주소 확인 바람 : " + e);
	            System.exit(1);
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
		}
		return bytes;
	}
}

/*****************************************************************/
class TurnonButton extends JButton implements ActionListener { // 버튼 액션 정의

	@Override
	public void actionPerformed(ActionEvent e) {
		addActionListener(this);
	 System.out.println("TurnOnButton 클릭됨");
	 
	 TurnOnLan LanAction = new TurnOnLan(); // TurnOnLan객체 생성
		LanAction.TurnOnLan(); // WakeOnLan함수 호출
		

	 
	}
	
	public void IP(String ip) { // 객체 타입으로 받기 위해 바꿈
		System.out.println("로딩한 아이피 : " + ip);
	} // 리턴 나갈꺼 없음 콘솔 내부적으로 표기만 할꺼임
	
	
	
	
}
/*****************************************************************/
class MouseEventClass extends Frame implements MouseListener { // 프레임 상에서 x와 y값을 알아내기 위해 생성
	
	 public static final int PORT = 9;  
	 
	public MouseEventClass() {
		addMouseListener(this);
		System.out.println("마우스 이벤트 클릭시 this값 : " + this);

	}
    
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		System.out.println("마우스 클릭");
		int x = e.getX(); // x값과 y값을 받아옴
		int y = e.getY();
		System.out.println("클릭된 x 값 : " + x);
		System.out.println("클릭된 Y 값 : " + y);
		
	}
	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		
	}
	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		
	}
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {

		
	}
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		
		
	}
}

/*****************************************************************/

public class WakeOne {

	
	
	public static void main(String[] args) {
		
		JFrame mainframe = new JFrame("WakeOnLan JAVA");
		JButton TurnonAll = new JButton("전체 부팅");
		JTextField ip_mac_view; // ip, mac주소 보여주는 텍스트 창
		TurnOnLan IP = new TurnOnLan();
		
		 TextLoading fileread = new TextLoading(); // 텍스트 가져오는 파일 별도로 만듦
		 List<String> contentlist = new ArrayList<String>();
		 contentlist = fileread.FileLoading();
		 
		mainframe.setDefaultCloseOperation(mainframe.EXIT_ON_CLOSE);
		mainframe.setVisible(true); // 창을 화면에 나타낼 것인지
		mainframe.setSize(1000, 500); // 프레임 사이즈 
		mainframe.setLayout(null);
		TurnonAll.setBounds(47,57,95,30); // x, y, height,width 
		mainframe.add(TurnonAll); // 메인 프레임에 버튼 추가해서 넣기
		// TurnonButton.setLocation(40, 0);
		System.out.println("프로그램 실행 콘솔");
		
		String ip_text =  contentlist.toString().replaceAll("^\\[", "")
				.replaceAll("\\]$", "").replace(", ", "");
	//	String ip_text = "123";
	
		ip_mac_view = new JTextField(ip_text); // 객체 생성 하면서 뷰 보여줌
		 // text field 내용
		
		ip_mac_view.setBounds(281,116,200,30); // 텍스트 필드 
		mainframe.add(ip_mac_view);
		
		MouseEventClass mouseevent = new MouseEventClass(); // 마우스 이벤트 객체 호출
		// 좌표 설정 할때 사용하려고 만듦
		mainframe.addMouseListener(mouseevent); // 프레임에 addmouseListenter를 호출하여 mousevent객체 넣음
		
		 TurnonButton Turnbutton = new TurnonButton();
		 TurnonAll.addActionListener(Turnbutton); // 버튼 클릭 이벤트 호출
	
	/*	TurnOnLan IpLoading = new TurnOnLan();
		Turnbutton.IP(IpLoading.TurnOnLan());*/
		
			
		 IpList IpButton = new IpList(); 
		 mainframe.add(IpButton.IpList()); // 메인 프레임에 추가
		 System.out.println("iplist 내용 : " + IpButton);
		
		/* TextLoading fileread = new TextLoading(); // 텍스트 가져오는 파일 별도로 만듦
*/		 // fileread.FileLoading();
		 
		/* List<String> contentlist = new ArrayList<String>();
		 contentlist = fileread.FileLoading();*/
		System.out.println("텍스트 내용 읽은 값 : " + contentlist.toString());
		String asString = contentlist.toString().replaceAll("^\\[", "")
				.replaceAll("\\]$", "").replace(", ", "");
		// System.out.println("길이 : " + asString.replaceAll(" ", " ").replace("", ""));
		System.out.println("길이 : " + asString);


	}








}
