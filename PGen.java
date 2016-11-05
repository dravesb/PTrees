
//Import things
import java.util.ArrayList; 
import java.io.FileWriter;
import java.util.Random;

public class PGen
{
    public static Integer count;
    private Random rand = new Random();
    

    //Main Methods
    public static void main(String[] args){
        PGen main = new PGen();
        try{
            main.run(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Double.parseDouble(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]));
        }
        catch(NumberFormatException e){
            System.out.println("Ensure you are passing the correct arguments");
        }
    }
    
    //Main wrapper method
    //public void run(int numOfGens, int numOfFamilies, int randos, String fileName){        
    public void run(String fileName, int numOfFamilies, int numOfGens, double prob_reprod,int min_offspring,int max_offspring, int randos){
        ArrayList<FamilyTree> fams = new ArrayList<FamilyTree>(numOfFamilies);
        for(int i = 0; i<numOfFamilies; i++){
            
            //Create the family tree with number of generations
            FamilyTree fam = new FamilyTree(numOfGens);
            
            //
            for(int j = 0; j<numOfGens-1; j++){
                fam.nextGen(j, prob_reprod, min_offspring, max_offspring);
            }
            fams.add(fam);
        }
        
        writeFile(fams, randos, fileName);
                
    }

    
    public void writeFile(ArrayList<FamilyTree> fams, int randoms, String fileName){
        FileWriter writer = null;
        FileWriter writer2 = null;
        try{
            writer = new FileWriter(fileName+".txt"); 

            for(int i = 0; i<fams.size(); i++){//all the families
                for(int j = 0; j<fams.get(i).getFamily().size(); j++){//all the generations
                    for(int k = 0; k<fams.get(i).getFamily().get(j).size(); k++){//all the people
                            writer.write((i + 1)+" "+fams.get(i).getFamily().get(j).get(k).toString() + "\n");
                    }
                }
            }
            
            for(int i = 1; i<randoms+1; i++){
                writer.write((fams.size()+i)+" 1 0 0 "+(rand.nextInt(2)+1)+"\n");
            }
            
            
            
            writer2 = new FileWriter("config.txt");
            
            writer2.write("PedigreeFile\t"+fileName+".viz.txt\nPedigreeName\tmyPed\nNameVariable\tName\nFatherVariable\tFather\nMotherVariable\tMother\nGenderVariable\tGender\t"+fileName+".viz.txt\nTextVariable\tName\t"+fileName+".viz.txt\nFemale\t2\nMale\t1\nPageOrientation\tlandscape\nVerboseMode\toff\nColorVariable\tColor");
            
            
        }
        catch(Exception e){
            System.out.println("A problem with writing the file occured.");
        }
        finally{
            try{
                writer.close();
                writer2.close();
            }
            catch(Exception e){
                System.out.println("A problem with writing the file occured.");
            }
        }
    }
    
}
