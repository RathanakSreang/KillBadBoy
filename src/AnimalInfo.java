import java.util.ArrayList;
import java.util.List;

public class AnimalInfo extends Things{
private List<Animal> animal;
	public AnimalInfo() {
		animal=new ArrayList<Animal>();
		//Good
		animal.add(new Animal(340,DataType.WIN_HEIGHT-40,TypeAnimal.GOOD1,0));
		animal.add(new Animal(380,DataType.WIN_HEIGHT-40,TypeAnimal.GOOD2,0));
		animal.add(new Animal(420,DataType.WIN_HEIGHT-40,TypeAnimal.GOOD3,0));
		animal.add(new Animal(460,DataType.WIN_HEIGHT-40,TypeAnimal.GOOD4,0));
		
		//BAd
		animal.add(new Animal(580,DataType.WIN_HEIGHT-40,TypeAnimal.BAD1,0));
		animal.add(new Animal(620,DataType.WIN_HEIGHT-40,TypeAnimal.BAD2,0));
		animal.add(new Animal(660,DataType.WIN_HEIGHT-40,TypeAnimal.BAD3,0));
		animal.add(new Animal(700,DataType.WIN_HEIGHT-40,TypeAnimal.BAD4,0));
		animal.add(new Animal(740,DataType.WIN_HEIGHT-40,TypeAnimal.BAD5,0));
	}
	public void draw(){
		for(Animal tem:animal){
			tem.draw();	
		}
	}
	
}
