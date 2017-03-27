
import static org.lwjgl.opengl.GL11.*;
import java.util.Random;
import org.newdawn.slick.opengl.Texture;
public class Animal extends Things{
private float Dx,Dy;
private float sizeX,sizeY; 
private Texture animal;
private TypeAnimal animlType;
private int maxSpeed;
private Random ran=new Random();
	public Animal(float x,float y,TypeAnimal type,int maxSpeed){
		this.x=x;
		this.y=y;
		this.maxSpeed=maxSpeed;
		if(maxSpeed>=1){
			Dx=RanSpeed(maxSpeed);
			Dy=RanSpeed(maxSpeed);	
		}
		animlType=type;
		animal=getImage(type);
		width=animal.getTextureWidth()/3;
		height=animal.getTextureHeight()/4;
		sizeX=.0f;
		sizeY=.50f;
	}
	private Texture getImage(TypeAnimal type){
		if(type==TypeAnimal.BAD1)return DataType.bad1;
		else if(type==TypeAnimal.BAD2)return DataType.bad2;
		else if(type==TypeAnimal.BAD3)return DataType.bad3;
		else if(type==TypeAnimal.BAD4)return DataType.bad4;
		else if(type==TypeAnimal.BAD5)return DataType.bad5;
		else if(type==TypeAnimal.GOOD1)return DataType.good1;
		else if(type==TypeAnimal.GOOD2)return DataType.good2;
		else if(type==TypeAnimal.GOOD3)return DataType.good3;
		else if(type==TypeAnimal.GOOD4)return DataType.good4;
		return null;
	}
	public TypeAnimal getTypeAnimal(){
		return animlType;
	}
	public void setMaxSpeed(int m){
		maxSpeed=m;
	}
	public int getMaxSpeed(){
		return maxSpeed;
	}
	private  float RanSpeed(int n){
		float num;
		num=ran.nextInt(n)+5;
		num=num/100;
		return num;
	}
	public void setDx(float dx){
		Dx=dx;
	}
	public void setDy(float dy){
		Dy=dy;
	}
	public float getDx(){
		return Dx;
	}
	public float getDy(){
		return Dy;
	}
	public void run(int delta){
		this.x+=delta*Dx;
		this.y+=delta*Dy;
		if((int)this.x>(DataType.WIN_WIDTH-width))Dx=-RanSpeed(maxSpeed);
		if((int)this.x<0)Dx=RanSpeed(maxSpeed);
		if((int)this.y>(DataType.WIN_HEIGHT-height)-50)Dy=-RanSpeed(maxSpeed);
		if((int)this.y<0)Dy=RanSpeed(maxSpeed);
	}
	public void draw(){
			animal.bind();
			glLoadIdentity();
			glBegin(GL_QUADS);
				glTexCoord2f(sizeX, sizeY);
				glVertex2f(x, y);
				glTexCoord2f(sizeX+animal.getWidth()/3, sizeY);
				glVertex2f(x+width, y);
				glTexCoord2f(sizeX+animal.getWidth()/3, sizeY+.25f);
				glVertex2f(x+width, y+height);
				glTexCoord2f(sizeX, sizeY+.25f);
				glVertex2f(x, y+height);
			glEnd();
			glLoadIdentity();
			UpdateSize();
	}
	private int getAnimationRow(){
		double dir=(Math.atan2(Dx, Dy)/(Math.PI/2)+2);
		int direction= (int)Math.round(dir)%4;
		return direction;
	}
	private void UpdateSize(){
				
		if(getAnimationRow()==0){
			sizeX=sizeX+animal.getWidth()/3;
			sizeY=0.75f;
		}
		else if(getAnimationRow()==1){
			sizeX=sizeX+animal.getWidth()/3;
			sizeY=0.25f;
		}
		else if(getAnimationRow()==2){
			sizeX=sizeX+animal.getWidth()/3;
			sizeY=0.0f;
		}else{
			sizeX=sizeX+animal.getWidth()/3;
			sizeY=0.50f;
		}
	}
	public boolean isKilling(Things other){
		boolean kill=false;
		if((other.getX()>=x-other.getWidth()/2)&&(other.getX()<x+width)&&(other.getY()>y-other.getHeight()+100)&&(other.getY()<y+height)){
		kill=true;	
		}
		return kill;
	}
	public Animal() {
		// TODO Auto-generated constructor stub
	}
	
}
