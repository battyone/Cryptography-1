package indexCalculus;
import java.math.BigInteger;
public class DiscreteLogDemo {
	/**********************************************************************
	 *Author: Lonique Alexander/Jay/Matt/Rachel/Navindra				  *
	 *Class:Cryptography CS 352/780 									  *
	 *Description:solve hard discrete log technique: Index Calculus	 	  *
	 *contact:Lonique.Alexander@gmail.com								  *
	 *last updated: 11/11/2010											  *									  
	 **********************************************************************/
    public static void main (String []args)
    { 
    	int factorBaseSize=45;
        BigInteger b, g, p;        				//local variable
        
        g = new BigInteger("2");																// non-primitive root mod p
        p = new BigInteger("247457076132467"); 	//the modulus prime p
        b = new BigInteger("92327518017224");     //B= g^x mod p
        
       System.out.println("\n x= "+IndexCalculus.indexCalculusSolv(g,p,b, factorBaseSize));  
    }
}