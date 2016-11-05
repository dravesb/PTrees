import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.io.*;

public class Pedigree
{
    private ArrayList<Person> subjects;

    /**
     * Constructor for objects of class Pedigree
     */
    public Pedigree()
    {
        subjects = new ArrayList<Person>();
    }

    public Pedigree(File infile){
        ArrayList<Person> people = new ArrayList<Person>();
        try{
            Scanner scan = new Scanner(infile);
            while(scan.hasNextLine()){
                
                //process line
                int fam = scan.nextInt();
                int indiv = scan.nextInt();
                int dadID = scan.nextInt();
                int momID = scan.nextInt();
                int sex = scan.nextInt();
                
                //Find mom and dad or set to null
                Person dad, mom;

                if(dadID == 0){
                    dad = null;
                }else{
                    dad = people.get(dadID - 1);
                }

                if(momID == 0){
                    mom = null;
                }else{
                    mom = people.get(momID - 1);
                }

                //create person and add to list
                Person p = new Person(fam, indiv, dad, mom, sex);           
                people.add(p);
                
                if(!scan.hasNext()){
                    break;
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("This file does not exist.");
            System.exit(1);
        }
        catch(Exception e){
            System.out.println("An error occured while reading the in file.");
            System.exit(1);
        }

        subjects = people;
        generateMAC();
    }

    private void generateMAC(){
        Random rand = new Random();
        for(int i = 0; i<subjects.size(); i++){//all subjects
            for(int j = 0; j<Statics.MAF.size(); j++){//all loci
                //If there is no parents
                if(subjects.get(i).getMom()==null || subjects.get(i).getDad()==null){
                    subjects.get(i).addMAC(getBinomial(2,Statics.MAF.get(j)));    
                }

                //If they have parents
                else{
                    
                    int dadAC = subjects.get(i).getDad().getMAC().get(j);
                    int momAC = subjects.get(i).getMom().getMAC().get(j);
                    

                    if(dadAC == 0 && momAC == 0){
                        subjects.get(i).addMAC(0);
                    }
                    else if((dadAC == 0 && momAC == 2) || (dadAC == 2 && momAC == 0)){
                        subjects.get(i).addMAC(1);
                    }
                    else if(dadAC == 2 && momAC == 2){
                        subjects.get(i).addMAC(2);
                    }
                    else{
                        //Zero and One case
                        if((dadAC == 0 && momAC == 1) || (dadAC == 1 && momAC == 0)){
                            if(rand.nextInt(2) == 0){
                                subjects.get(i).addMAC(0);
                            }else{
                                subjects.get(i).addMAC(1);
                            }
                        }
                        else if((dadAC == 2 && momAC == 1) || (dadAC == 1 && momAC == 2)){
                            if(rand.nextInt(2) == 0){
                                subjects.get(i).addMAC(1);
                            }else{
                                subjects.get(i).addMAC(2);
                            }
                        }

                        else{
                            int test = rand.nextInt(4);
                            if(test == 0){
                                subjects.get(i).addMAC(0);
                            }
                            else if(test == 1 || test == 2){
                                subjects.get(i).addMAC(1);
                            }
                            else{
                                subjects.get(i).addMAC(2);
                            }
                        }

                    }

                }
            }
        }

    }

    private int getBinomial(int n, double p){
        int x = 0;
        for(int i = 0; i<n; i++){
            if(Math.random()<p){
                x++;
            }
        }
        return x;
    }

    private int getRandom(){
        Random gen = new Random();
        return gen.nextInt(2)+1;
    } 

    public void writePedFile(String fileName){
        FileWriter writer = null;
        try{
            writer = new FileWriter(fileName+".ped");
            for(int i = 0; i<subjects.size(); i++){
                if(i%10 == 0){
                    System.out.println("Writing out person " + (i+1));
                }
                writer.write(subjects.get(i).toString() + "\n");
            }
        }
        catch(Exception e){
            System.out.println("An error occured while writting the file out.");
        }
        finally{
            try{
                writer.close();
            }
            catch(Exception e){
                System.out.println("An error occured while writting the file out.");
            }
        }
    }

    public void writeMapFile(String fileName){

        //Generate BP distances 
        ArrayList<Integer> amount = new ArrayList<Integer>();
        int total = 0;
        for(int i = 0; i<Statics.snpPer.size(); i++){
            total = total + (int)(Statics.numOfSNPS*Statics.snpPer.get(i)/100);
            amount.add((int)(Statics.numOfSNPS*Statics.snpPer.get(i)/100));
        }

        int remainder = Statics.numOfSNPS - total;
        amount.set(21, amount.get(21)+remainder); 

        ArrayList<Long> BP = new ArrayList<Long>();
        ArrayList<Integer> chrome = new ArrayList<Integer>();
        for(int i = 0; i<22; i++){
            BP.addAll(generateBP(amount.get(i), cumDistance(i), cumDistance(i+1)));
            chrome.addAll(generateChrome(amount.get(i), i));
        }

        //write out to a file
        FileWriter writer = null;

        try{
            writer = new FileWriter(fileName+".map");
            for(int i = 0; i<Statics.numOfSNPS; i++){
                writer.write(chrome.get(i)+1 + "  snp" + i + " 0 " + BP.get(i)+"\n");
            }

        }

        catch(Exception e){
            System.out.println("An error occured while writting the file out.");
        }
        finally{
            try{
                writer.close();
            }
            catch(Exception e){
                System.out.println("An error occured while writting the file out.");
            }
        }
    }

    private ArrayList<Long> generateBP(int amount, long min, long max){
        ArrayList<Long> distances = new ArrayList<Long>(amount);
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            distances.add(nextLong(random, max)+min);
        }
        Collections.sort(distances);
        return distances;
    }

    private ArrayList<Integer> generateChrome(int amount, int whichChrome){
        ArrayList<Integer> ret = new ArrayList<Integer>(amount);
        for (int i = 0; i < amount; i++) {
            ret.add(whichChrome);
        }
        return ret;
    }

    private long cumDistance(int n){
        long ret = 0;
        for(int i = 0; i<n; i++){
            ret = ret + Statics.snpLength.get(i);
        }
        return ret; 
    }

    private long nextLong(Random rng, long n) {
        // error checking and 2^x checking removed for simplicity.
        long bits, val;
        do {
            bits = (rng.nextLong() << 1) >>> 1;
            val = bits % n;
        } while (bits-val+(n-1) < 0L);
        return val;
    }

}
