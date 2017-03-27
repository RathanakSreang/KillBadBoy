
import static org.lwjgl.opengl.GL11.*;
import java.awt.Font;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

@SuppressWarnings("deprecation")
public class Help extends Things{
	private TrueTypeFont helpinfo;
	private Texture help,play;
	private HelpType htype=HelpType.LOAD;
	private double hightscore,score;
	private int level,life;
	private Animal animal[]=new Animal[9];
	private boolean isClick=false;
	public double getHightscore() {
		return hightscore;
	}
	public void setData(double hc,double sc,int level,int life){
		hightscore=hc;
		score=sc;
		this.level=level;
		this.life=life;
		
	}
	public void setHightscore(double hightscore) {
		this.hightscore = hightscore;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public HelpType getHtype() {
		return htype;
	}
	public void setHtype(HelpType htype) {
		this.htype = htype;
		play=getImage(htype);
	}
	public void setisClick(boolean is){
		isClick=is;
	}
	public boolean getisClick(){
		return isClick;
	}
	public Help() {
		initFont();
		help=DataType.backMenu;
		play=getImage(htype);
		x=50;
		y=50;
		width=help.getTextureWidth();
		height=help.getTextureHeight();
		hightscore=0;
		score=0;
		level=0;
		life=0;
		
		//Good
		animal[0]=new Animal(x+50,y+35*5,TypeAnimal.GOOD1,0);
		animal[1]=new Animal(x+50,y+35*6,TypeAnimal.GOOD2,0);
		animal[2]=new Animal(x+50,y+35*7,TypeAnimal.GOOD3,0);
		animal[3]=new Animal(x+50,y+35*8,TypeAnimal.GOOD4,0);
		
		//BAd
		animal[4]=new Animal(x+50,y+35*9,TypeAnimal.BAD1,0);
		animal[5]=new Animal(x+50,y+35*10,TypeAnimal.BAD2,0);
		animal[6]=new Animal(x+50,y+35*11,TypeAnimal.BAD3,0);
		animal[7]=new Animal(x+50,y+35*12,TypeAnimal.BAD4,0);
		animal[8]=new Animal(x+50,y+35*13,TypeAnimal.BAD5,0);
		
	}
	private Texture getImage(HelpType type){
		if(type==HelpType.LOAD)return DataType.menuPlay;
		else if(type==HelpType.NEXT)return DataType.menuNext;
		else if(type==HelpType.RESTART)return DataType.menuRestart;
		else if(type==HelpType.HELP)return DataType.menuResume;
		return null;
	}
private void initFont(){
	Font awtFont= new Font("Times New Roman", Font.BOLD, 14);
	helpinfo= new TrueTypeFont(awtFont,true);
}
public void draw(){
	help.bind();
	glLoadIdentity();
	glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(x, y);
		glTexCoord2f(help.getWidth(), 0);
		glVertex2f(x+width, y);
		glTexCoord2f(help.getWidth(), help.getHeight());
		glVertex2f(x+width, y+height);
		glTexCoord2f(0, help.getHeight());
		glVertex2f(x, y+height);
	glEnd();
	Infomation();
	ClickButton();
}
public void run(int delta){
	Input();
}
public void Infomation(){
	int temx=help.getTextureWidth()/2-40;
	if(htype==HelpType.LOAD){
		helpinfo.drawString(x+temx, y+50*3, "Levels: "+ level);
		helpinfo.drawString(x+50, y+50*4, "Your Score: "+ score);
		helpinfo.drawString(x+help.getTextureWidth()/2+50, y+50*4, "Hight Score: "+hightscore);
		helpinfo.drawString(x+temx, y+50*5, "Your Life: "+life);
		helpinfo.drawString(x+temx, y+50*6, "F1: Help ");
		helpinfo.drawString(x+temx, y+50*7, "S: Start Game        O: Sound On/Off ");
		helpinfo.drawString(x+temx, y+50*8, "P: Pause/Play ");
		helpinfo.drawString(x+temx, y+50*9, "Escap: Exit ");
	}
	if(htype==HelpType.NEXT){
		helpinfo.drawString(x+temx, y+50*3, "Levels: "+level);
		helpinfo.drawString(x+50, y+50*4, "Your Score: "+score);
		helpinfo.drawString(x+help.getTextureWidth()/2+50, y+50*4, "Hight Score: "+hightscore);
		helpinfo.drawString(x+temx, y+50*5, "Your Life: "+life);
		helpinfo.drawString(x+temx, y+50*6, "F1: Help ");
		helpinfo.drawString(x+temx, y+50*7, "S: Start Game        O: Sound On/Off");
		helpinfo.drawString(x+temx, y+50*8, "P: Pause/Play ");
		helpinfo.drawString(x+temx, y+50*9, "Escap: Exit ");
	}
	if(htype==HelpType.RESTART){
		helpinfo.drawString(x+temx, y+50*3, "Levels: "+level);
		helpinfo.drawString(x+50, y+50*4, "Your Score: "+score);
		helpinfo.drawString(x+help.getTextureWidth()/2+50, y+50*4, "Hight Score: "+hightscore);
		helpinfo.drawString(x+temx, y+50*5, "Your Life: "+life);
		helpinfo.drawString(x+temx, y+50*6, "F1: Help ");
		helpinfo.drawString(x+temx, y+50*7, "S: Start Game        O: Sound On/Off ");
		helpinfo.drawString(x+temx, y+50*8, "P: Pause/Play ");
		helpinfo.drawString(x+temx, y+50*9, "Escap: Exit ");
	}
	if(htype==HelpType.HELP){
		helpinfo.drawString(x+temx-80, y+35*3, "Your Mission is kill all bad guy ");
		helpinfo.drawString(x+50, y+35*4, "There Four good girl and Five bad guy and they are: ");
		helpinfo.drawString(x+150, y+37*5, "She is a good girl and her name is : Kanteka");
		helpinfo.drawString(x+150, y+37*6, "She is a good girl and her name is : Bopha");
		helpinfo.drawString(x+150, y+37*7, "She is a good girl and her name is : Sreyoun");
		helpinfo.drawString(x+150, y+37*8, "She is a good girl and her name is : Samnang");
		helpinfo.drawString(x+150, y+37*9, "He is a bad guy and his name is  : TyTouch");
		helpinfo.drawString(x+150, y+37*10, "He is a bad guy and his name is  : Vy");
		helpinfo.drawString(x+150, y+37*11, "He is a bad guy and his name is  :Mab");
		helpinfo.drawString(x+150, y+37*12, "He is a bad guy and his name is  :Mandang");
		helpinfo.drawString(x+150, y+37*13, "He is a bad guy and his name is  :Darong");
	}
}
private void ClickButton(){
	play.bind();
	glLoadIdentity();
	glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(x+(help.getTextureWidth()-play.getTextureWidth())/2, y+50);
		glTexCoord2f(play.getWidth(), 0);
		glVertex2f(x+(help.getTextureWidth()+play.getTextureWidth())/2, y+50);
		glTexCoord2f(play.getWidth(), play.getHeight());
		glVertex2f(x+(help.getTextureWidth()+play.getTextureWidth())/2,y+50+play.getTextureHeight());
		glTexCoord2f(0,  play.getHeight());
		glVertex2f(x+(help.getTextureWidth()-play.getTextureWidth())/2,y+50+play.getTextureHeight());
	glEnd();
	glLoadIdentity();
	if(htype==HelpType.HELP){
		for(Animal tem:animal){
			tem.draw();	
		}
	}
}
private void Input(){
	if(Mouse.isButtonDown(0)){
		if((Mouse.getX()>x&&Mouse.getX()<x+help.getTextureWidth())&&(DataType.WIN_HEIGHT-Mouse.getY()>y&&DataType.WIN_HEIGHT-Mouse.getY()<y+help.getTextureHeight())){
				float mx=Mouse.getDX();
				float my=Mouse.getDY();
				y=y-my;
				x=x+mx;
				for(int i=0;i<9;i++){
					animal[i].setX(x+50);
					animal[i].setY(y+35*(5+i));
			}
				if(x>DataType.WIN_WIDTH-100)x=DataType.WIN_WIDTH-100;
				else if(x<-help.getTextureWidth()+100)x=-help.getTextureWidth()+100;
				if(y>DataType.WIN_HEIGHT-100)y=DataType.WIN_HEIGHT-100;
				if(y<-help.getTextureHeight()+100)y=-help.getTextureHeight()+100;
		}
	}
	if(Mouse.isButtonDown(0)&&htype==HelpType.LOAD&&(Mouse.getX()>x+(help.getTextureWidth()-play.getTextureWidth())/2&&Mouse.getX()<x+(help.getTextureWidth()+play.getTextureWidth())/2)&&(DataType.WIN_HEIGHT-Mouse.getY()> y+50&&DataType.WIN_HEIGHT-Mouse.getY()<y+50+play.getTextureHeight())){
		isClick=true;
		
	}
	if(Mouse.isButtonDown(0)&&htype==HelpType.NEXT&&(Mouse.getX()>x+(help.getTextureWidth()-play.getTextureWidth())/2&&Mouse.getX()<x+(help.getTextureWidth()+play.getTextureWidth())/2)&&(DataType.WIN_HEIGHT-Mouse.getY()> y+50&&DataType.WIN_HEIGHT-Mouse.getY()<y+50+play.getTextureHeight())){
		isClick=true;
	}
	if(Mouse.isButtonDown(0)&&htype==HelpType.RESTART&&(Mouse.getX()>x+(help.getTextureWidth()-play.getTextureWidth())/2&&Mouse.getX()<x+(help.getTextureWidth()+play.getTextureWidth())/2)&&(DataType.WIN_HEIGHT-Mouse.getY()> y+50&&DataType.WIN_HEIGHT-Mouse.getY()<y+50+play.getTextureHeight())){
		isClick=true;
	}
	if(Mouse.isButtonDown(0)&&htype==HelpType.HELP&&(Mouse.getX()>x+(help.getTextureWidth()-play.getTextureWidth())/2&&Mouse.getX()<x+(help.getTextureWidth()+play.getTextureWidth())/2)&&(DataType.WIN_HEIGHT-Mouse.getY()> y+50&&DataType.WIN_HEIGHT-Mouse.getY()<y+50+play.getTextureHeight())){
		isClick=true;
	}
	
}
}
