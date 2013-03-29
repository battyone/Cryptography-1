package indexCalculus;
import java.math.BigInteger;
import java.util.Vector;
import java.security.SecureRandom;

public class IndexCalculus {
    /* the model --> b= g^x mod p <-- the model */
	static final BigInteger negOne = new BigInteger("-1");
    static final BigInteger ZERO = new BigInteger("0");
    static final BigInteger ONE = new BigInteger("1");
    static final BigInteger TWO = new BigInteger("2");
    
    static BigInteger matrix[][];											//static so only can be one copy. this going to take up lot memory. 

    public static BigInteger indexCalculusSolv(BigInteger g, BigInteger p,BigInteger b, int bs){
    	System.out.println("stage 1: Finding logarithms of the factor base");
    	int expArray[],ix=0, fb[]= new int[bs];								// local variables
        BigInteger setB=ONE, nextPrime=ONE, randS, x=ZERO, l, logarithm[];
   
        SecureRandom randomGenerator = new SecureRandom();

       // stage 1: part A: create the factor base B
        //while (setB.compareTo(b) < 1) { // 0->setB <=B & 1 ->setB<B
        while(ix<bs){
            nextPrime = nextPrime.nextProbablePrime();
            if(isGoodPrime(nextPrime, p)){
	           // setB = setB.multiply(nextPrime);
	            fb[ix]=nextPrime.intValue();
	            ix++;
            }
        }
        System.out.print("B=");Matrix.printArray(fb);
             
        //stage 1 part B: compute the relations
        logarithm=computeRelation(fb, g, p); Matrix.printBoard(matrix);
        
        //stage 2 part B: solve linear system with prime modulus(s) p-1
        //logarithm=SolveLinearSystemMod(p, logarithm);
        logarithm = Matrix.GaussianEliminationModP(matrix, logarithm, p);
       
        System.out.println("stage 3: Compute Log_g b=x");
        /*stage 3 part A: choose a random s , 1<= s<=p-2 
         and compute l=b*g^s*/
        do{
        	randS = new BigInteger(p.bitLength()-1,randomGenerator);
        	//randS = BigInteger.valueOf(Math.abs(randomGenerator.nextInt()));
        	l=(b.multiply(g.pow(randS.intValue()))).mod(p);
        	expArray=BigIntegerMath.factorWRTFB(fb, l);
        }while(expArray[0]<0);
        
        System.out.println("Random s= "+ randS+" and b*g^s mod p= "+ l);
        Matrix.printArray(expArray);
        
        /*Final Stage: stage 3 part B: 
        then if log_g b=c_1*log_gp_1+ c_2*log_g*p_2+....+ c_m*log_g*p_m-randS*/
        for(int i =0; i<expArray.length;i++){
        	//System.out.println("\n before x= "+x);
        	x=x.add(logarithm[i].multiply(BigInteger.valueOf(expArray[i])));
        	//System.out.println("after x= "+x);
        }	
        
        x=(x.subtract(randS)).mod( (p.subtract(ONE)) );
        return x;
    }

    public static BigInteger[] computeRelation(int fb[], BigInteger g,BigInteger p) {                         
    	int sizeFB= fb.length, relation =0;
        BigInteger dr, randX;

        BigInteger[] logs= new BigInteger[sizeFB];
        matrix=new BigInteger[sizeFB][sizeFB];
       
        SecureRandom randomGenerator = new SecureRandom();
  
        while (relation<sizeFB){
            randX = new BigInteger(p.bitLength()-1,randomGenerator);
        	//randX = BigInteger.valueOf(Math.abs(randomGenerator.nextInt()));
        	//randX = BigInteger.valueOf(randomGenerator.nextInt(65476)+51);
        	
            dr = g.modPow(randX, p); 						// g^x(random)mod p
          
            int []exp = BigIntegerMath.factorWRTFBGTOL(fb, dr);	//factor with respect v factor base

            //if pass add data to matrix "a" & "b" in ax =b
            if(!(exp[0]<0)){
            	System.out.println("found smoothness when x= "+randX);
            	//set b
            	logs[relation]=randX;
            	//set up a with respect 
                for(int col=0;col<exp.length;col++)                   
                    matrix[relation][col]=BigInteger.valueOf(exp[col]);        
            	//if successful another relation is found add 1
                relation++;
            }// End of if statement
            
        }// end of main-while-loop
        
        return logs;
    }//end of compute Relation
    
    public static BigInteger[] SolveLinearSystemMod(BigInteger p, BigInteger[] logarithm){
    	int ix=0;
    	BigInteger pMinusOne=p.subtract(ONE);
    	BigInteger pMinusOneFac[]={pMinusOne, ZERO}, chineseRemanderResult[]={ZERO};
    	Vector<BigInteger[]> chineseRemanderV = new Vector<BigInteger[]>();
    	 
    	
    	 do{
         	if(pMinusOneFac[ix].isProbablePrime(1)){
         		System.out.println("uahdfhoiasdjfiopaj="+pMinusOneFac[ix]);
         		chineseRemanderV.add(Matrix.GaussianEliminationModP(matrix,logarithm, pMinusOneFac[ix]));
         		
         		ix++;
         	}else pMinusOneFac=BigIntegerMath.PollardRhoFactor(pMinusOne);
         }while(ix<pMinusOneFac.length);
    	 ix=0;
    	 while(ix<chineseRemanderV.size()-1)
    		 chineseRemanderResult= BigIntegerMath.solveCRT(chineseRemanderV.elementAt(0), chineseRemanderV.elementAt(1), pMinusOneFac);
    	 
    	 return chineseRemanderResult;
    }
    
    // The legendre and jacobi symbols
    public static boolean isGoodPrime(BigInteger g,BigInteger p){
    	BigInteger seven=BigInteger.valueOf(7);
    	BigInteger eight=seven.add(ONE);
    	
    	if(g.equals(TWO)){
    		if(p.mod(eight).equals(ONE)||p.mod(eight).equals(seven))
    			return true;
    		else return false;	
    	}
    	
    	//if it is even return true
    	if(((p.subtract(ONE).divide(TWO)).multiply((g.subtract(ONE).divide(TWO)))).mod(TWO).equals(ZERO))
    		return true;
    	else return false;
    }
    
}// end of class