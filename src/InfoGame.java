
import static org.lwjgl.opengl.GL11.*;
import java.awt.Font;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.Texture;

public class InfoGame extends Things{
	private Texture text;
	@SuppressWarnings({ "deprecation" })
	private TrueTypeFont HeighScore,Score,timeer,life,badguy,goodguy;
	private double SC;
	private int Tim,sec,Li,level;
	public InfoGame() {
		width=DataType.WIN_WIDTH;
		height=50;
		x=0;
		y=DataType.WIN_HEIGHT-height;
		text=DataType.bar;
		InitFont();
		level=1;
		SC=0;
		Tim=0;
		sec=0;
		Li=10;
	}
	public int getLife(){
		return Li;
	}
	public void setLevel(int hs){
		level=hs;
	}
	public int getLevel(){
		return level;
	}
	public void setScore(double hs){
		SC=hs;
	}
	public double getScore(){
		return SC;
	}
	public void setLife(int hs){
		Li=hs;
	}
	public void setTime(int t){
		Tim=t;
	}
	public int getTime(){
	return Tim;	
	}
	private void Header(){
		text.bind();
		glLoadIdentity();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(x, y);
			glTexCoord2f(text.getWidth(), 0);
			glVertex2f(x+width, y);
			glTexCoord2f(text.getWidth(), text.getHeight());
			glVertex2f(x+width,y+height);
			glTexCoord2f(0, text.getHeight());
			glVertex2f(x,y+height);
		glEnd();
		glLoadIdentity();
	}
	@SuppressWarnings("deprecation")
	private void HeighScore(){
		HeighScore.drawString(10f, DataType.WIN_HEIGHT-40, "Levels: "+level);
	}
	@SuppressWarnings("deprecation")
	private void Score(){
		Score.drawString(10f, DataType.WIN_HEIGHT-20, "Your Score:"+SC);
	}
	@SuppressWarnings("deprecation")
	private void Timer(){
		timeer.drawString(180f, DataType.WIN_HEIGHT-40, "Time: "+ Tim);
	}
	@SuppressWarnings("deprecation")
	private void Life(){
		life.drawString(180f, DataType.WIN_HEIGHT-20, "Life: "+ Li);
	}
	@SuppressWarnings("deprecation")
	private void GoodGuy(){
		goodguy.drawString(270f, DataType.WIN_HEIGHT-30, "Good Guy: ");
	}
	@SuppressWarnings("deprecation")
	private void BadGuy(){
		badguy.drawString(520f, DataType.WIN_HEIGHT-30, "Bad Guy: ");
	}
	//Override Methods
	public void draw(){
		Header();
		HeighScore();
		Score();
		Timer();
		Life();
		BadGuy();
		GoodGuy();
	}
	//Override Methods
	public void run(int delta){
		sec++;
		if(sec>60){
			Tim++;
			sec=0;
		}
	}
	@SuppressWarnings("deprecation")
	private void InitFont(){
		Font awtFont= new Font("Times New Roman", Font.BOLD, 14);
		HeighScore= new TrueTypeFont(awtFont,true);
		Score= HeighScore;
		timeer=HeighScore;
		life=HeighScore;
		badguy=HeighScore;
		goodguy=HeighScore;
	}
}
