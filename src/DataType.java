import java.io.IOException;
import javax.swing.JOptionPane;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class DataType {
	//Window Width Height
	public static final int WIN_WIDTH=800;
	public static final int WIN_HEIGHT=600;
	//Guy
	public static final Texture bad1=getImage("bad/bad1");
	public static final Texture bad2=getImage("bad/bad2");
	public static final Texture bad3=getImage("bad/bad3");
	public static final Texture bad4=getImage("bad/bad4");
	public static final Texture bad5=getImage("bad/bad5");
	public static final Texture good1=getImage("good/good1");
	public static final Texture good2=getImage("good/good2");
	public static final Texture good3=getImage("good/good3");
	public static final Texture good4=getImage("good/good4");
	//Blood
	public static final Texture blood=getImage("blood");
	//Background Main
	public static final Texture back=getImage("back");
	public static final Texture bar=getImage("bar");
	//Menu
	public static final Texture backMenu=getImage("backMenu");
	public static final Texture menuPlay=getImage("menuPlay");
	public static final Texture menuNext=getImage("menuNext");
	public static final Texture menuRestart=getImage("menuRestart");
	public static final Texture menuResume=getImage("menuResume");
	// Icon
	public static final Texture iconSoundOn=getImage("iconSoundOn");
	public static final Texture iconSoundOff=getImage("iconSoundOff");
	public static final Texture iconPause=getImage("iconPause");
	public static final Texture iconUnPause=getImage("iconUnPause");
	//Stick for kill
	public static final Texture stickDown=getImage("stickDown");
	public static final Texture stickUp=getImage("stickUp");
	private static Texture getImage(String s){
		try {
			return TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/"+s+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File :"+s+":,"+e.toString());
			JOptionPane.showMessageDialog(null,"Anable Load file "+s+".png");
		}
		return null;
	}
}
