import javax.swing.JButton;
import javax.swing.JFrame;



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
		

		
	
	}

}
