
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

public class Blood extends Things{
private Texture blood;
private int timeCreate=20;
private ArrayList<Blood> tem;
	public Blood(List<Blood> tem,Things animal) {
		x=animal.getX()-animal.getWidth()/2;
		y=animal.getY()-animal.getHeight()/2;
		blood=DataType.blood;
		width=100;
		height=100;
		this.tem=(ArrayList<Blood>) tem;
	}
	public void draw(){
		blood.bind();
		glLoadIdentity();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(x, y);
			glTexCoord2f(1, 0);
			glVertex2f(x+width, y);
			glTexCoord2f(1, 1);
			glVertex2f(x+width, y+height);
			glTexCoord2f(0, 1);
			glVertex2f(x, y+height);
		glEnd();
		glLoadIdentity();
	}
public void run(int delta){
	
	if(--timeCreate<1){
		tem.remove(this);
	}
}
}
