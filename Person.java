import java.util.ArrayList;
import java.util.Random;

public class Person
{
    private int famID, ID, sex;
    private Person paternal, maternal;
    private ArrayList<Integer> MAC;//Minor Allele Count
    private ArrayList<ArrayList<Integer>> genom;
    private Random rand;
    /**
     * Constructor for objects of class Person
     */
    public Person(int famid, int id, Person pat, Person mat, int seX)
    {
        famID = famid;
        ID = id;
        sex = seX;//1 for male 2 for female
        paternal = pat;
        maternal = mat;
        MAC = new ArrayList<Integer>();
    }

    public Person getMom(){
        return maternal;
    }

    public Person getDad(){
        return paternal;
    }

    public ArrayList<Integer> getMAC(){
        return MAC;
    }

    public void addMAC(Integer val){
        MAC.add(val);
    }

    public int getID(){
        return ID;
    }

    public String MACtoString(){
        String s ="";
        for(int i = 0; i<MAC.size(); i++){
            if(MAC.get(i)==0){
                s= s+" AA";
            }
            else if(MAC.get(i)==1){
                s= s+" AC";
            }
            else{
                s= s+" CC";
            }
        }
        return s;
    }

    public String toString(){
        int dadID, momID;

        if(paternal == null){
            dadID = 0;
        }
        else{
            dadID = paternal.getID();
        }

        if(maternal == null){
            momID = 0;
        }
        else{
            momID = maternal.getID();
        }

        return famID + " "+ID +" "+ dadID + " "+ momID +" "+ sex + " " + "-9" + MACtoString();
    }

}
