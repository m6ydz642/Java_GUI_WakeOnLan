import java.awt.Frame;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/*****************************************************************/
class MouseEventClass extends Frame implements MouseListener {
	public MouseEventClass() {
		addMouseListener(this);
		System.out.println("this값 : " + this);
		
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
		
		mainframe.setDefaultCloseOperation(mainframe.EXIT_ON_CLOSE);
		mainframe.setVisible(true); // 창을 화면에 나타낼 것인지
		mainframe.setSize(1000, 500); // 프레임 사이즈 
		mainframe.setLayout(null);
		TurnonButton.setBounds(52,45,95,30);
		mainframe.add(TurnonButton); // 메인 프레임에 버튼 추가해서 넣기
		// TurnonButton.setLocation(40, 0);
		System.out.println("프로그램 실행 콘솔");
		
		MouseEventClass mouseevent = new MouseEventClass(); // 마우스 이벤트 객체 호출
		// 좌표 설정 할때 사용하려고 만듦
		mainframe.addMouseListener(mouseevent); // 프레임에 addmouseListenter를 호추하여 mousevent객체 넣음
		
	
	}

}
