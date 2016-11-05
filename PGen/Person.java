import java.util.ArrayList;
import java.util.Random;

public class Person
{
    // instance variables
    private ArrayList<Person> children;
    private Integer ID, genNum, sex, mom, dad, spouse;
    private Random rand;
    public Person()
    {
        rand = new Random();
        mom = 0;
        dad = 0;
        ID = null;
        sex = null;
        spouse = -1;
        children = new ArrayList<Person>();
    }
    
    public Person(Integer Dad, Integer Mom, Integer Sex, Integer Spouse, Integer id)
    {
        rand = new Random();
        mom = Mom;
        dad = Dad;
        ID = id;
        sex = Sex;
        spouse = Spouse;
        children = new ArrayList<Person>();
    }
    
    
    public Person(Integer Dad, Integer Mom)
    {
        rand = new Random();
        mom = Mom;
        dad = Dad;
        ID = null;
        sex = null;
        spouse = -1;
        children = new ArrayList<Person>();
    }

    public ArrayList<Person> haveChildren(int min_offspring, int max_offspring){
        //Here should be min_offspring + rand.nextInt(max_offspring - min_offspring)
        Integer numOfChildren = min_offspring+rand.nextInt(max_offspring - min_offspring); 
        //Integer numOfChildren = 1; 
        for(int i = 0; i<numOfChildren; i++){
            
            Person kid;
            
            //Set parents
            if(sex == 1){
                kid = new Person(ID,spouse, rand.nextInt(2)+1,-1, PGen.count+1);
                PGen.count++;
            }else{
                kid = new Person(spouse, ID, rand.nextInt(2)+1,-1, PGen.count+1);
                PGen.count++;
            }
            children.add(kid);
        }

        return children;
    }

    public String toString(){
        return ID + " " + dad + " " + mom + " " + sex; 
    }
    
    public void addChild(Person p){
        children.add(p);
    }
    
    public void addChildren(ArrayList<Person> p){
        children.addAll(p);
    }

    public void setID(int i){
        ID = i;
    }
    
    public void setSex(int i){
        sex = i;
    }
    
    public Integer getID(){
        return ID;
    }

    public void setGenNum(int i){
        genNum = i;
    }
    
    public ArrayList<Person> getChildren(){
        return children;
    }
    
    public void setSpouse(int i){
        spouse = i;
    }
    
    public int getSex(){
        return sex;
    }
    
    public int getSpouse(){
        return spouse;
    }
    
}
