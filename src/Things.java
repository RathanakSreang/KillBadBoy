//The Super class of Game
public class Things {
//Data member 
protected float x,y;
protected float width,height;
	public Things() {
		// TODO Auto-generated constructor stub
	}
	//Override method
	public void draw(){
		
	}
	//Override method
	public void run(int delta){
		
	}
	//Set get method
	public float getX() {
		return x;
	}
	//Set get method
	public void setX(float x) {
		this.x = x;
	}
	//Set get method
	public float getY() {
		return y;
	}
	//Set get method
	public void setY(float y) {
		this.y = y;
	}
	//Set get method
	public float getWidth() {
		return width;
	}
	//Set get method
	public void setWidth(float width) {
		this.width = width;
	}
	//Set get method
	public float getHeight() {
		return height;
	}
	//Set get method
	public void setHeight(float height) {
		this.height = height;
	}
	//Set get method
	public boolean isKilling(Things other){
		return false;
	}
}
