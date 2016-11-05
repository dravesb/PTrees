import java.util.ArrayList;
import java.util.Random; 

public class FamilyTree
{
    ArrayList<ArrayList<Person>> family;
    Random rand;

    public FamilyTree(int generations){
        rand = new Random();
        family = new ArrayList<ArrayList<Person>>();
        
        for(int i = 0; i<generations; i++){
            ArrayList<Person> p = new ArrayList<Person>();
            family.add(p);
        }
        
        Person p1 = new Person(0,0,1,-1, 1);
        
        family.get(0).add(p1);
        PGen.count = 1;

    }

    public void addPerson(Person p, int gen){
        family.get(gen).add(p);
    }

    public void nextGen(int currentGeneration, double prob_reprod, int min_offspring, int max_offspring){
        //Loop through current generation and see who has children
        Person spouse;
        ArrayList<Person> spouses = new ArrayList<Person>();
        
        //Create spouses 
        for(int i = 0; i<family.get(currentGeneration).size(); i++){
            //if the person in question will reproduce (and is not a founder)
            if(rand.nextDouble()<prob_reprod || currentGeneration == 0 ){
                //Create spouse
                
                //If there spouse is male
                if(family.get(currentGeneration).get(i).getSex() == 1){
                    spouse = new Person(0,0,2,family.get(currentGeneration).get(i).getID(), PGen.count+1);
                    PGen.count++;
                }else{//If there spouse is female
                    spouse = new Person(0,0,1,family.get(currentGeneration).get(i).getID(), PGen.count+1);
                    PGen.count++;
                }
                
                //Add spouse to the list and set spouse
                spouses.add(spouse);
                family.get(currentGeneration).get(i).setSpouse(spouse.getID());
            }
        }
        int initialGenerationSize = family.get(currentGeneration).size(); 
        family.get(currentGeneration).addAll(spouses);
        
        //Have children and add them to the next generation
        for(int i = 0; i<initialGenerationSize; i++){
            
            if(family.get(currentGeneration).get(i).getSpouse() != -1){
                family.get(currentGeneration+1).addAll(family.get(currentGeneration).get(i).haveChildren(min_offspring, max_offspring)); 
            }
        }
    }
    
    public ArrayList<ArrayList<Person>> getFamily(){
        return family;
    }
}
