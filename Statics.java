import java.util.Random; 
import java.util.ArrayList;

public class Statics
{
    public static ArrayList<Double> MAF, snpPer;
    public static ArrayList<Integer> snpLength;
    public static Integer numOfSNPS;
    Random rand;
    
    
    public Statics(Integer numSNPS){
        MAF = new ArrayList<Double>();
        snpPer = new ArrayList<Double>();
        snpLength = new ArrayList<Integer>();
        rand = new Random();
        numOfSNPS = numSNPS; 
        for(int i = 0; i<numOfSNPS; i++){
            int randomNum = rand.nextInt((50 - 5) + 1) + 5;
            MAF.add(randomNum/100.0);
        }
        
        snpLength.add(249250621);
        snpPer.add(8.31);
        
        snpLength.add(243199373);
        snpPer.add(8.17);
        
        snpLength.add(198022430);
        snpPer.add(6.71);
        
        snpLength.add(191154276);
        snpPer.add(6.43);
        
        snpLength.add(180915260);
        snpPer.add(6.08);
        
        snpLength.add(171115067);
        snpPer.add(5.74);
        
        snpLength.add(159138663);
        snpPer.add(5.34);
        
        snpLength.add(146364022);
        snpPer.add(4.92);
        
        snpLength.add(141213431);
        snpPer.add(4.72);
        
        snpLength.add(135534747);
        snpPer.add(4.55);
        
        snpLength.add(135006516);
        snpPer.add(4.52);
        
        snpLength.add(133851895);
        snpPer.add(4.45);
        
        snpLength.add(115169878);
        snpPer.add(3.84);
        
        snpLength.add(107349540);
        snpPer.add(3.58);
        
        snpLength.add(102531392);
        snpPer.add(3.37);
        
        snpLength.add(90354753);
        snpPer.add(2.99);
        
        snpLength.add(81195210);
        snpPer.add(2.65);
        
        snpLength.add(78077248);
        snpPer.add(2.56);
        
        snpLength.add(59128983);
        snpPer.add(2.14);
        
        snpLength.add(63025520);
        snpPer.add(2.10);
        
        snpLength.add(48129895);
        snpPer.add(1.58);
        
        snpLength.add(51304566);
        snpPer.add(1.67);
        
        
    }
    
    
}
