package indexCalculus;
import java.math.BigInteger;

public class BigIntegerMath {
	static final BigInteger ZERO = new BigInteger("0");
	static final BigInteger ONE = new BigInteger("1");
	static final BigInteger TWO = new BigInteger("2");
	static final BigInteger negONE = new BigInteger("-1");
	
	//a^-1 mod m when gcd(a, m)=1
   public static BigInteger modInv(BigInteger a, BigInteger m) {
      if(a.compareTo(ZERO)<0)
    	  return ZERO;
      
      return a.modInverse(m);
   }
	
	//Computes the least nonnegative residues modulo m (the remainder)
   public static BigInteger lnr(BigInteger b, BigInteger m) {
      BigInteger answer=b.mod(m);
      if(answer.compareTo(ZERO)<0)
    	  answer.add(m);
      
      return answer;
   }
   
	//Chinese remainder theorem 
   	//k= (a-b)*n^-1 mod m  and x =b+nk
   	//it going return Chinese remainder theorem of to array1 and array2 
	public static BigInteger[] solveCRT(BigInteger[] logarithm1,BigInteger[] logarithm2, BigInteger[] modulus) {
		BigInteger[] result = new BigInteger[logarithm1.length], residue=new BigInteger[2];	
		BigInteger solution = ZERO, M=ONE;
		
		// get the product of the modulus
		for (int i = 0; i < modulus.length; i++)M = M.multiply(modulus[i]);
		
		for(int ix=0; ix<logarithm1.length;ix++){
			
			residue[0]=logarithm1[ix];residue[1]=logarithm2[ix];
			
			// compute Chinese Remainder Theorem
			for (int i = 0; i < residue.length; i++) {
				BigInteger Mi = M.divide(modulus[i]);
				solution = solution.add( residue[i].multiply(Mi).multiply(Mi.modInverse(modulus[i])));
			}
			// store the result in array
			result[ix] = lnr(solution, M);
			//reset solution 
			solution=ZERO;
		}//end of for loop
		
		return result;
	}
	
	//brute force factoring with respect to factor base
	//going from lease to greatest. 
	public static int[] factorWRTFB(int fb[], BigInteger n){	
		int expCount=0,ix=0,exp[]= new int [fb.length];
			
        while(ix< fb.length){
            while(n.mod(BigInteger.valueOf(fb[ix])).equals(ZERO)){
            	expCount++;
            	n=n.divide(BigInteger.valueOf(fb[ix]));
            }
            exp[ix]=expCount;
            ix++;expCount=0;
        }
        /*if it was not smooth then we set the first element 
        to negative on to tell main program it was not smooth*/
        if(!(n.equals(ONE)))exp[0]=-1;
        //if get this far the it is a smooth factor base
        return exp;
	}
	
	//brute force factoring with respect to factor base
	//going from greatest to least. i think this one will run faster
	public static int[] factorWRTFBGTOL(int fb[], BigInteger n){	
		int expCount=0,ix=fb.length-1,exp[]= new int [fb.length];
			
        while(ix>=0){
            while(n.mod(BigInteger.valueOf(fb[ix])).equals(ZERO)){
            	expCount++;
            	n=n.divide(BigInteger.valueOf(fb[ix]));
            }
            exp[ix]=expCount;
            ix--;expCount=0;
        }
        /*if it was not smooth then we set the first element 
        to negative on to tell main program it was not smooth*/
        if(!(n.equals(ONE)))exp[0]=-1;
        //if get this far the it is a smooth factor base
        return exp;
	}
	
	//factor p-1 where p is prime// use quadratic x^2 +1
	public static BigInteger[] PollardRhoFactor(BigInteger n){
		PollardRhoF fac= new PollardRhoF();
		return fac.factoring(n);	
	}
}
