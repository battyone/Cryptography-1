package indexCalculus;

import java.math.BigInteger;

public class Factor {
	private static int[] exponent;
	public static int[] factorWithBase(BigInteger num,int[] base){
		exponent=new int[base.length];
		
		int k=0;
		while(k<base.length&&num.compareTo(BigInteger.ONE)>0){
			if((num.mod(new BigInteger(""+base[k]))).equals(BigInteger.ZERO)){
				exponent[k]++;
				System.out.println(k+" "+exponent[k]);
				num=num.divide(new BigInteger(""+base[k]));
				System.out.println(num);
			}
			else
				k++;
		}
		if(num.compareTo(BigInteger.ONE)>0||num.compareTo(BigInteger.ONE)<0)
			return null;
		else return exponent;
	}
}
