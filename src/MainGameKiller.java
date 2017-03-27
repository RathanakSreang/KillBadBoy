
import static org.lwjgl.opengl.GL11.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;


public class MainGameKiller {
private boolean isRunning=true;
private List<Things> animal;
private List<Blood> blood;
private Things killer;
private long lastTime;
private Texture text;
private InfoGame infogame;
private AnimalInfo animalInfo;
private double score=0,highScore=0;
private int life=5;
private int levelGame=1;
private Help helpInfo;
private Audio wavMan,wavWoman;
private Texture play,soundon;
private boolean soundoff=false;
private Random ran;
private DataSave datasave=new DataSave();
private StateGame stateGame=StateGame.LOAD;
	public MainGameKiller() {
		Start();
	}
private void Start(){
	InitDisplay();
	InitGL();
	lastTime=getTime();
	text=DataType.back;
	helpInfo=new Help();
	//reset
	//datasave.save(new File("saving.xml"));
	datasave.load(new File("saving.xml"));
	//System.out.println(datasave.getHightscore()+","+datasave.getScore()+","+datasave.getLevels());
	highScore=datasave.getHightscore();
	score=datasave.getScore();
	levelGame=datasave.getLevels();
	life=datasave.getLife();
	init();
	 ran=new Random();
	animal=new ArrayList<Things>();
	blood=new ArrayList<Blood>();
	infogame=new InfoGame();
	animalInfo=new AnimalInfo();
	killer=new Killer();
	while(isRunning){
		Render();
		Background();
		Input();
		HelpClick();
		switch(stateGame){
		case LOAD:
			showHideMouse(true);
			helpInfo.setData(highScore, score, levelGame, life);//,HelpType.LOAD);
			helpInfo.draw();
			helpInfo.run(0);
			break;
		case START:
			gameRun();
			Running();
			setPicSoundPause();
			break;
		case NEXT:
			setInfo();
			helpInfo.setData(highScore, score, levelGame, life);
			helpInfo.draw();
			helpInfo.run(0);
			break;
		case PAUSE:
			gameRun();
			setPicSoundPause();
			break;
		case RESTART:
			setInfo();
			levelGame=1;
			life=5;
			score=0;
			helpInfo.setData(highScore, score, levelGame, life);
			helpInfo.draw();
			helpInfo.run(0);
			break;
		case HELP:
			helpInfo.draw();
			helpInfo.run(0);
			break;
		}
			drawcontrol();
		Update();
		if(Display.isCloseRequested())isRunning=false;
	}
	Exited();
}
private void gameRun(){
		for(Things tem:animal){
			tem.draw();
		}
		for(int i=0;i<blood.size();i++){
			blood.get(i).draw();
		}
		infogame.draw();
		killer.draw();
		animalInfo.draw();
		winning();
		onKilling();
		isDie();
}
private void Running(){
	int delta=getDelta();
	for(Things tem:animal){
		tem.run(delta);
	}
	for(int i=0;i<blood.size();i++){
		blood.get(i).run(delta);
	}
	infogame.run(delta);
	killer.run(delta);
	AddBad(infogame.getLevel()+1);
}
private void isDie(){
	if(infogame.getLife()<=0){
		stateGame=StateGame.RESTART;
		showHideMouse(true);
	if(helpInfo.getHtype()==HelpType.START){
		helpInfo.setHtype(HelpType.RESTART);
	}
		
	}
}
private void Loaded(){
	initStart(5);
}
private void Nexted(){
	int lif=infogame.getLife();
	double sc=infogame.getScore();
	int level=infogame.getLevel();
	initStart(2+level);
	infogame.setScore(sc);
	infogame.setLife(lif+1);
	infogame.setLevel(level+1);
}
private void initStart(int perfor){
	animal.removeAll(animal);
	blood.removeAll(blood);
	animal.clear();
	blood.clear();	
	for(int i=0;i<levelGame;i++){
		animal.add(new Animal(ran.nextInt(DataType.WIN_WIDTH-50),ran.nextInt(DataType.WIN_HEIGHT-50),TypeAnimal.BAD1,perfor));	
		animal.add(new Animal(ran.nextInt(DataType.WIN_WIDTH-50),ran.nextInt(DataType.WIN_HEIGHT-50),TypeAnimal.BAD2,perfor));
		animal.add(new Animal(ran.nextInt(DataType.WIN_WIDTH-50),ran.nextInt(DataType.WIN_HEIGHT-50),TypeAnimal.BAD3,perfor));
		animal.add(new Animal(ran.nextInt(DataType.WIN_WIDTH-50),ran.nextInt(DataType.WIN_HEIGHT-50),TypeAnimal.BAD4,perfor));
		animal.add(new Animal(ran.nextInt(DataType.WIN_WIDTH-50),ran.nextInt(DataType.WIN_HEIGHT-50),TypeAnimal.BAD5,perfor));
		animal.add(new Animal(ran.nextInt(DataType.WIN_WIDTH-50),ran.nextInt(DataType.WIN_HEIGHT-50),TypeAnimal.GOOD1,perfor));
		animal.add(new Animal(ran.nextInt(DataType.WIN_WIDTH-50),ran.nextInt(DataType.WIN_HEIGHT-50),TypeAnimal.GOOD2,perfor));
		animal.add(new Animal(ran.nextInt(DataType.WIN_WIDTH-50),ran.nextInt(DataType.WIN_HEIGHT-50),TypeAnimal.GOOD3,perfor));
		animal.add(new Animal(ran.nextInt(DataType.WIN_WIDTH-50),ran.nextInt(DataType.WIN_HEIGHT-50),TypeAnimal.GOOD4,perfor));
	}
	infogame.setScore(score);
	infogame.setLevel(levelGame);
	infogame.setLife(life);	
	infogame.setTime(0);
}
private void AddBad(int perfor){
	if(infogame.getTime()%60==0&&infogame.getTime()!=0){
		Random ran=new Random();
		animal.add(new Animal(ran.nextInt(DataType.WIN_WIDTH),ran.nextInt(DataType.WIN_HEIGHT-50),TypeAnimal.BAD1,perfor));	
		animal.add(new Animal(ran.nextInt(DataType.WIN_WIDTH),ran.nextInt(DataType.WIN_HEIGHT-50),TypeAnimal.BAD2,perfor));
		animal.add(new Animal(ran.nextInt(DataType.WIN_WIDTH),ran.nextInt(DataType.WIN_HEIGHT-50),TypeAnimal.BAD3,perfor));
		animal.add(new Animal(ran.nextInt(DataType.WIN_WIDTH),ran.nextInt(DataType.WIN_HEIGHT-50),TypeAnimal.BAD4,perfor));
		animal.add(new Animal(ran.nextInt(DataType.WIN_WIDTH),ran.nextInt(DataType.WIN_HEIGHT-50),TypeAnimal.BAD5,perfor));
	}
}
private void setInfo(){
	int life=infogame.getLife();
	double sc=infogame.getScore();
	int level=infogame.getLevel();
	levelGame=level+1;
	this.life=life+1;
	score=sc;
	if(highScore<score)highScore=score;
}
private void Input(){
	if(Mouse.getX()>DataType.WIN_WIDTH-(play.getTextureWidth()+soundon.getTextureWidth()+20)&&Mouse.getX()<DataType.WIN_WIDTH&&
			DataType.WIN_HEIGHT-Mouse.getY()>0&&DataType.WIN_HEIGHT-Mouse.getY()<play.getTextureHeight()+10){
		showHideMouse(true);
	}else if(stateGame==StateGame.START){
		showHideMouse(false);
	}
	while(Mouse.next()){
		if(Mouse.isButtonDown(0)){
			if(Mouse.getX()>DataType.WIN_WIDTH-(play.getTextureWidth()+soundon.getTextureWidth()+20)&&Mouse.getX()<DataType.WIN_WIDTH-(soundon.getTextureWidth()+10)&&
					DataType.WIN_HEIGHT-Mouse.getY()>0&&DataType.WIN_HEIGHT-Mouse.getY()<soundon.getTextureHeight()+10){
				if(!soundoff){
					soundoff=true;
				}else{
					soundoff=false;
				}	
			}
			if(Mouse.getX()>DataType.WIN_WIDTH-(play.getTextureWidth()+10)&&Mouse.getX()<DataType.WIN_WIDTH&&
					DataType.WIN_HEIGHT-Mouse.getY()>0&&DataType.WIN_HEIGHT-Mouse.getY()<play.getTextureHeight()+10){
				if(stateGame==StateGame.START){
					stateGame=StateGame.PAUSE;
					showHideMouse(true);
				}else{
					stateGame=StateGame.START;
					showHideMouse(false);
				}
			}
		}
	}
	
	//Keyboard
	while(Keyboard.next()){
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			Exited();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F1)){
			if(stateGame==StateGame.START){
				stateGame=StateGame.HELP;
				helpInfo.setHtype(HelpType.HELP);
				showHideMouse(true);
			}
			else{
				stateGame=StateGame.START;
				showHideMouse(false);
				
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			if(stateGame==StateGame.LOAD){
				Loaded();
				stateGame=StateGame.START;
				helpInfo.setHtype(HelpType.START);
			}
			else if(stateGame==StateGame.NEXT){
				Nexted();
				stateGame=StateGame.START;
				helpInfo.setHtype(HelpType.START);
			}
			else if(stateGame==StateGame.RESTART){
				life=5;
				Loaded();
				stateGame=StateGame.START;
				helpInfo.setHtype(HelpType.START);
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_O)){
			if(soundoff){
				soundoff=false;
				setPicSoundPause();
			}else{
				soundoff=true;
				setPicSoundPause();
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_P)){
			if(stateGame==StateGame.PAUSE){
				stateGame=StateGame.START;
				showHideMouse(false);
				setPicSoundPause();
			}
			else{
				stateGame=StateGame.PAUSE;
				showHideMouse(true);
				setPicSoundPause();
			}
		}
	}
	
}
private void showHideMouse(boolean b){
	if(b){
		try {
			Mouse.setNativeCursor(null);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(!b){//Mouse.setGrabbed(!b);
		try {
			Mouse.setNativeCursor(new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null));
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
private void winning(){
	boolean win=false;
	for(Things tem:animal){
		Animal an=(Animal)tem;
		if(an.getTypeAnimal()==TypeAnimal.BAD1|an.getTypeAnimal()==TypeAnimal.BAD2|an.getTypeAnimal()==TypeAnimal.BAD3|
				an.getTypeAnimal()==TypeAnimal.BAD4|an.getTypeAnimal()==TypeAnimal.BAD5){
			win=true;
			break;
		}
	}
	if(!win){
		showHideMouse(true);
		stateGame=StateGame.NEXT;
		helpInfo.setHtype(HelpType.NEXT);
	}
}
private void onKilling(){
	if(Mouse.isButtonDown(0)){
		int count=0;
		for( int i=0;i<animal.size();i++){
			Things tem=animal.get(i);
			Animal an=(Animal)tem;
			if(tem.isKilling(killer)){
				animal.remove(tem);
				if(an.getTypeAnimal()==TypeAnimal.GOOD1|an.getTypeAnimal()==TypeAnimal.GOOD2|an.getTypeAnimal()==TypeAnimal.GOOD3|an.getTypeAnimal()==TypeAnimal.GOOD4){
					infogame.setLife(infogame.getLife()-1);
					infogame.setScore(infogame.getScore()-3);
					if(!soundoff)wavWoman.playAsSoundEffect(1f, 1.f, false);
				}else{
					infogame.setScore(infogame.getScore()+1);
					if(!soundoff)wavMan.playAsSoundEffect(1f, 1.f, false);
					count++;
				}
				blood.add(new Blood(blood,tem));
				//break;
			}
		}
		if(count>1){
			infogame.setScore(infogame.getScore()+count*2);
		}
	}
}
private long getTime(){
	return ((Sys.getTime()*1000)/Sys.getTimerResolution());
}
private int getDelta(){
	long currentTime=Sys.getTime();
	int delta=(int)(currentTime-lastTime);
	lastTime=currentTime;
	return delta;
}
private void Render(){
	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
}
private void Update(){
	Display.update();
	Display.sync(60);
}
private void InitGL(){
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(0, DataType.WIN_WIDTH, DataType.WIN_HEIGHT, 0, -1, 1);
	glMatrixMode(GL_MODELVIEW);
	glEnable(GL_TEXTURE_2D);
	
	//remove background texture
	glEnable(GL_BLEND);
	glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	  
}

private void Background(){
	text.bind();
	glLoadIdentity();
	glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2i(0, 0);
		glTexCoord2f(text.getWidth(), 0);
		glVertex2i(DataType.WIN_WIDTH, 0);
		glTexCoord2f(text.getWidth(), text.getHeight());
		glVertex2i(DataType.WIN_WIDTH,DataType.WIN_HEIGHT);
		glTexCoord2f(0,  text.getHeight());
		glVertex2i(0,DataType.WIN_HEIGHT);
	glEnd();
	glLoadIdentity();
}
private void HelpClick(){
	if(helpInfo.getHtype()==HelpType.LOAD){
		if (helpInfo.getisClick()){
			Loaded();
			stateGame=StateGame.START;
			helpInfo.setHtype(HelpType.START);
			helpInfo.setisClick(false);
		}
		else stateGame=StateGame.LOAD;
	}
	if(helpInfo.getHtype()==HelpType.NEXT){
		if (helpInfo.getisClick()){
			Nexted();
			stateGame=StateGame.START;
			helpInfo.setHtype(HelpType.START);
			helpInfo.setisClick(false);
		}
	}
	if(helpInfo.getHtype()==HelpType.RESTART){
		if (helpInfo.getisClick()){
			life=5;
			Loaded();
			stateGame=StateGame.START;
			helpInfo.setHtype(HelpType.START);
			helpInfo.setisClick(false);
		}
	}
	if(helpInfo.getHtype()==HelpType.HELP){
		if (helpInfo.getisClick()){
			stateGame=StateGame.START;
			helpInfo.setHtype(HelpType.START);
			helpInfo.setisClick(false);
		}
	}
}
private void InitDisplay() {		
	try {
		Display.setDisplayMode(new DisplayMode(DataType.WIN_WIDTH,DataType.WIN_HEIGHT));
		Display.setTitle("Kill Bad Guy Game: By Sreang Rathanak(sreangrathanak@yahoo.com,016921007)");
		Display.setVSyncEnabled(true);
		Display.create();
	} catch (LWJGLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Exited();
	}
	
	try {
	    ByteBuffer[] iconss = new ByteBuffer[2];
	    iconss[0] = loadIcon("res/windowIconUp.png", 16, 16);
	    iconss[1] = loadIcon("res/windowIconButton.png", 32, 32);
	    Display.setIcon(iconss);
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
	setPicSoundPause();
}
public ByteBuffer loadIcon(String filename, int width, int height) throws IOException {
    BufferedImage image = ImageIO.read(new File(filename)); // load image

    // convert image to byte array
    byte[] imageBytes = new byte[width * height * 4];
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            int pixel = image.getRGB(j, i);
            for (int k = 0; k < 3; k++) // red, green, blue
                imageBytes[(i*16+j)*4 + k] = (byte)(((pixel>>(2-k)*8))&255);
            imageBytes[(i*16+j)*4 + 3] = (byte)(((pixel>>(3)*8))&255); // alpha
        }
    }
    return ByteBuffer.wrap(imageBytes);
}
private void setPicSoundPause(){
	play=null;
	soundon=null;
	if(stateGame!=StateGame.PAUSE){
		play=DataType.iconUnPause;
	}else{
		play=DataType.iconPause;
	}
	if(!soundoff){
		soundon=DataType.iconSoundOn;
	}else{
		soundon=DataType.iconSoundOff;
	}
}
private void drawcontrol(){
	
	play.bind();
	glLoadIdentity();
	glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(DataType.WIN_WIDTH-play.getTextureWidth(), 0);
		glTexCoord2f(play.getWidth(), 0);
		glVertex2f(DataType.WIN_WIDTH, 0);
		glTexCoord2f(play.getWidth(), play.getHeight());
		glVertex2f(DataType.WIN_WIDTH, play.getTextureHeight());
		glTexCoord2f(0,  play.getHeight());
		glVertex2f(DataType.WIN_WIDTH-play.getTextureWidth(), play.getTextureHeight());
	glEnd();
	glLoadIdentity();
	
	soundon.bind();
	glLoadIdentity();
	glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(DataType.WIN_WIDTH-play.getTextureWidth()-soundon.getTextureWidth()-10, 0);
		glTexCoord2f(soundon.getWidth(), 0);
		glVertex2f(DataType.WIN_WIDTH-play.getTextureWidth()-10, 0);
		glTexCoord2f(soundon.getWidth(),soundon.getHeight());
		glVertex2f(DataType.WIN_WIDTH-play.getTextureWidth()-10, soundon.getTextureHeight());
		glTexCoord2f(0, soundon.getHeight());
		glVertex2f(DataType.WIN_WIDTH-play.getTextureWidth()-soundon.getTextureWidth()-10, soundon.getTextureHeight());
	glEnd();
	glLoadIdentity();
}
private void Exited(){
	datasave.setHightscore(highScore);
	datasave.setScore(score);
	datasave.setLevels(levelGame);
	datasave.setLife(life);
	datasave.save(new File("saving.xml"));
	Display.destroy();
	AL.destroy();
	System.exit(0);
}
public static void main(String[]args){
	new MainGameKiller();
}
private void init(){
	try{
		wavMan = AudioLoader.getAudio("WAV",ResourceLoader.getResourceAsStream("sound/man.wav"));
		wavWoman = AudioLoader.getAudio("WAV",ResourceLoader.getResourceAsStream("sound/womanscream.wav"));
	}catch(IOException e){
		e.printStackTrace();
	}
}
}
