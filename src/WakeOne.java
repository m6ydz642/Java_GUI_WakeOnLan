import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

/*class IP_MAC extends JTextComponent implements SwingConstants { // text필드 정의
	
}*/

/*****************************************************************/
class TurnonButton extends JButton implements ActionListener { // 버튼 액션 정의

	@Override
	public void actionPerformed(ActionEvent e) {
		addActionListener(this);
	 System.out.println("TurnOnButton 클릭됨");
	// System.out.println("겟소스 : " + e.getSource());
	}
	
	public void IP(String ip) {
		System.out.println("로딩한 아이피 : " + ip);
	}
	
}
/*****************************************************************/
class MouseEventClass extends Frame implements MouseListener { // 프레임 상에서 x와 y값을 알아내기 위해 생성
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
		JButton TurnonButton = new JButton("TurnOn");
		JTextField ip_mac_view; // ip, mac주소 보여주는 텍스트 창
		
		mainframe.setDefaultCloseOperation(mainframe.EXIT_ON_CLOSE);
		mainframe.setVisible(true); // 창을 화면에 나타낼 것인지
		mainframe.setSize(1000, 500); // 프레임 사이즈 
		mainframe.setLayout(null);
		TurnonButton.setBounds(50,50,95,30); // x, y, height,width 
		mainframe.add(TurnonButton); // 메인 프레임에 버튼 추가해서 넣기
		// TurnonButton.setLocation(40, 0);
		System.out.println("프로그램 실행 콘솔");
		
		String ip_text = "192.168.0.10"; // 일단 하드코딩 된 상태로 둠
		String mac_text = "12:3D:3E:1A:3F"; // 일단 하드코딩 된 상태로 둠
		ip_mac_view = new JTextField(ip_text); // 객체 생성 하면서 뷰 보여줌
		
		ip_mac_view.setBounds(342,53,200,30);
		mainframe.add(ip_mac_view);
		
		MouseEventClass mouseevent = new MouseEventClass(); // 마우스 이벤트 객체 호출
		// 좌표 설정 할때 사용하려고 만듦
		mainframe.addMouseListener(mouseevent); // 프레임에 addmouseListenter를 호출하여 mousevent객체 넣음
		
		 TurnonButton Turnbutton = new TurnonButton();
		TurnonButton.addActionListener(Turnbutton); // 버튼 클릭 이벤트 호출
		
		Turnbutton.IP(ip_text); // 프로그램 실행하면서 로딩한 아이피 보여주기
		// 나중에 어떤 txt 파일을 읽어서 텍스트 필드로 보여주는 걸로 바꿀 예정 
		
		
		
		
	}

}
