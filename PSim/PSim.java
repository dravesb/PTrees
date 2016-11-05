import java.io.File;

public class PSim
{
    public static void main(String[] args){
     try{
         PSim writer = new PSim();
         File file = new File(args[0]);
         Statics stat = new Statics(Integer.parseInt(args[1]));
         writer.run(file, args[2]);
         
     }
     catch(Exception e){
         System.out.println("This file does not exist");
         System.exit(1);
     }
    }
    
    public void run(File infile, String outFileName){
        Pedigree ped = new Pedigree(infile);
        System.out.println("Pedigree has been generated.");
        ped.writePedFile(outFileName);
        System.out.println("Ped File has been generated.");
        ped.writeMapFile(outFileName);
        System.out.println("Map File has been generated.");
    }
}
