import java.awt.Frame;
import java.awt.event.MouseListener;

/*****************************************************************/
class MouseEvent extends Frame implements MouseListener { // 프레임 상에서 x와 y값을 알아내기 위해 생성
	
	 public static final int PORT = 9;  
	 
	public MouseEvent() {
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