
import static org.lwjgl.opengl.GL11.*;
import javax.swing.JOptionPane;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public class Killer extends Things{
	// Declare data member
	private Texture killer,killer1;
	private boolean buttondown;
	//Override Methods
	public void draw(){
		if(buttondown){
			killer.bind();
			glLoadIdentity();
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2f(x, y);
				glTexCoord2f(killer.getWidth(),0);
				glVertex2f(x+width, y);
				glTexCoord2f(killer.getWidth(),killer.getHeight());
				glVertex2f(x+width, y+height);
				glTexCoord2f(0, killer.getHeight());
				glVertex2f(x, y+height);
			glEnd();
		glLoadIdentity();
		}else if(!buttondown){
			killer1.bind();
			glLoadIdentity();
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2f(x, y);
				glTexCoord2f(killer.getWidth(),0);
				glVertex2f(x+width, y);
				glTexCoord2f(killer.getWidth(),killer.getHeight());
				glVertex2f(x+width, y+height);
				glTexCoord2f(0, killer.getHeight());
				glVertex2f(x, y+height);
			glEnd();
		glLoadIdentity();
		}
	}
	//Override Methods
	public void run(int delta){
			if(Mouse.getEventButton()!=-1){
				if(Mouse.isButtonDown(0)){
					buttondown=true;
				}else{
					buttondown=false;
				}
			}
			if((Mouse.getX()>0&&Mouse.getX()<DataType.WIN_WIDTH)&&(Mouse.getY()>0&&Mouse.getY()<DataType.WIN_HEIGHT)){
				this.x=Mouse.getX()-width/2;
				this.y=DataType.WIN_HEIGHT-Mouse.getY()-height/2;
			}
	}
	//Method
	public Killer() {
		setX(100);
		setY(100);
		killer=DataType.stickDown;
		killer1=DataType.stickUp;
		buttondown=false;
		setWidth(47);
		setHeight(130);
		try {
			Mouse.setNativeCursor(new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null));
		} catch (LWJGLException e) {
			JOptionPane.showMessageDialog(null,e.toString()+"Mouse Exeption");
			e.printStackTrace();
		}
	}

}
