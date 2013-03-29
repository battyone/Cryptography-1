package Crypto;

import java.math.BigInteger;

public class PollardRho {
	public static BigInteger euclid(BigInteger a,BigInteger b){
		if(a.compareTo(BigInteger.ZERO)==0)
			return b;
		while(b.compareTo(BigInteger.ZERO)!=0){
			if(a.compareTo(b)==1)
				a=a.subtract(b);
			else
				b=b.subtract(a);
		}
		return a;
	}
	
	public static BigInteger function(BigInteger x,BigInteger modulo){
		x=x.multiply(x);
		x=x.add(new BigInteger("40282366920938463463374607431768211457"));
		
		return x.mod(modulo);
	}
	
	public static void main(String[]args){
		
		BigInteger num=new BigInteger("2");
		num=num.pow(128);
		num=num.add(BigInteger.ONE);
		System.out.println(num);
		System.out.println(factor(num));
		//System.out.println(euclid(new BigInteger(""+378),new BigInteger(""+526)));
	}
	
	public static BigInteger factor(BigInteger num){
		BigInteger x,y,d;
		x=new BigInteger("2");
		y=new BigInteger("2");
		d=new BigInteger("1");
		while(d.equals(BigInteger.ONE)){
			x=function(x,num);
			y=function(y,num);
			y=function(y,num);
			d=euclid((x.subtract(y)).abs(),num);
		}
		if(d==num)
			return null;
		return d;
	}
}
