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

		System.out.println("프로그램 실행 콘솔");
		
		String alltext = contentlist.toString().replaceAll("^\\[", "")
				.replaceAll("\\]$", "").replace(", ", "");
		String[] ip_text = alltext.split(";");
		System.out.println("ip address : " + ip_text[0]); // 0번째는 아이피
	//	공백 등 toString 자체 양식 제거 (오버라이딩으로 재정의 해서 쓰려고 했는데 안됨 ㅡ.ㅡ;;)
	
		String[] macaddress_text = alltext.split(";");
		System.out.println("mac address : " + macaddress_text[1]); // 배열의 첫번째는 맥주소
		ip_mac_view = new JTextField(ip_text[0]); // 객체 생성 하면서 뷰 보여줌
	
		 // text field 내용
;
		
		
		/*
		 * ip_text[0]는 아이피 
		 * macaddress_text[1]에는 맥주소 들어있음
		 * 
		 * 어쩌다 보니 0 2 4 6 8등 짝수 형태는 아이피
		 * 홀수 형태는 mac주소가 됨
		 * */
		
		
		
		ip_mac_view.setBounds(157,152,200,30); // 텍스트 필드 
		mainframe.add(ip_mac_view);
		
	/*	MouseEvent mouseevent = new MouseEvent(); // 마우스 이벤트 객체 호출
		// 좌표 설정 할때 사용하려고 만듦
		mainframe.addMouseListener(mouseevent); // 프레임에 addmouseListenter를 호출하여 mousevent객체 넣음
		*/
		
		 TurnonButton Turnbutton = new TurnonButton();
		 TurnonAll.addActionListener(Turnbutton); // 버튼 클릭 이벤트 호출
	

		 IpList IpButton = new IpList(); 
		 mainframe.add(IpButton.IpList()); // 메인 프레임에 추가
		 System.out.println("iplist 내용 : " + IpButton);


		 System.out.println("아이피 요소 갯수 길이 : " + ip_text.length);

		for (int i = 0; i<ip_text.length-1; i+=2) { 		 // 출력 테스트용
			int j =0;
			j = i+1; // j에다 i값 플러스
			System.out.println("i값[" + i + "]" + "아이피 주소 : " + ip_text[i]);
			System.out.println("j값[" + j + "]" + "맥 주소 : " + macaddress_text[j]);
			
		} 
	
	}








}
