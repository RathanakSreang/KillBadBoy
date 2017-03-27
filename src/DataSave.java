
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class DataSave {
private double hightscore,score;
private int levels,life;

	public DataSave() {
		// TODO Auto-generated constructor stub
		hightscore=0;
		score=0;
		levels=1;
		life=5;
	}
	public DataSave(double hs,float sc,int level,int life) {
		// TODO Auto-generated constructor stub
		hightscore=hs;
		score=sc;
		levels=level;
		this.life=life;
	}
	public void load(File loadFile){
		try {
			SAXBuilder reader=new SAXBuilder();
			Document document=reader.build(loadFile);
			Element root=document.getRootElement();
			for(Object block: root.getChildren()){
				Element e=(Element)block;
				 hightscore=Double.parseDouble(e.getAttributeValue("Hightscore"));
				 score=Double.parseDouble(e.getAttributeValue("Score"));
				 levels=Integer.parseInt(e.getAttributeValue("Levels"));
				 life=Integer.parseInt(e.getAttributeValue("Life"));
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void save(File saveFile){
		Document document=new Document();
		Element element=new Element("Killer");
		document.setRootElement(element);
			
				Element block=new Element("Killer");
				block.setAttribute("Hightscore",String.valueOf((float)getHightscore()));
				block.setAttribute("Score",String.valueOf((float)getScore()));
				block.setAttribute("Levels",String.valueOf((int)getLevels()));
				block.setAttribute("Life",String.valueOf((int)getLife()));
				element.addContent(block);
			
		XMLOutputter output=new XMLOutputter();
		try {
			output.output(document, new FileOutputStream(saveFile) );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public double getHightscore() {
		return hightscore;
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
	public int getLevels() {
		return levels;
	}
	public void setLevels(int levels) {
		this.levels = levels;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
}
