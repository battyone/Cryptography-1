package Crypto;

import java.math.BigInteger;
import java.util.Random;

public class PrimalityTest {
	public static void main(String[]args){
		int k=513;
		double[] tests=new double[10];
		int intM=1;
		BigInteger num=new BigInteger("2");
		BigInteger m=new BigInteger(""+intM);
		//BigInteger five=new BigInteger(""+5);
		BigInteger n=num.pow(k);
		n=n.multiply(m);
		n=n.add(BigInteger.ONE);
		double probability=0;
		while(probability==0.0){
			intM++;
			m=new BigInteger(""+intM);
			while((num.pow(k)).multiply(m).bitLength()>513)
				k--;
			n=num.pow(k);
			n=n.multiply(m);
			n=n.add(BigInteger.ONE);
			probability=primalityTest(n);
			System.out.println(k+" "+intM);
		}
		for(int i=0;i<tests.length;i++){
			probability=primalityTest(n);
			tests[i]=probability;
		}
		for(int i=0;i<tests.length;i++){
			System.out.print(tests[i]+" ");
		}
		System.out.println();
		System.out.println("Probability that number is prime is "+probability);
		System.out.println("Number is "+n);
		System.out.println("bit length is:" + n.bitLength());
	}
	
	public static double primalityTest(BigInteger num){
		Random rand=new Random(System.currentTimeMillis());
		int intA=rand.nextInt(1999999999)+50;
		BigInteger temp=num.subtract(BigInteger.ONE);
		int k=factorTwo(temp);
		BigInteger part1=new BigInteger(""+2);
		part1=part1.pow(k);
		BigInteger m=temp.divide(part1);
		BigInteger a=new BigInteger(""+intA);
		BigInteger b=a.pow(m.intValue());
		b=b.mod(num);
		//System.out.print(0+":");
		//System.out.println(b);
		
		if(b.compareTo(BigInteger.ONE)==0||b.compareTo(new BigInteger("-1"))==0){
			//System.out.println(1);
			return .75;
		}
		for(int i=1;i<k-1;i++){
			//System.out.print(i+":");
			b=b.multiply(b);
			b=b.mod(num);
			//System.out.println(b);
			if(b.compareTo(new BigInteger("-1"))==0){
				System.out.println(2);
				return .75;
			}
			if(b.compareTo(BigInteger.ONE)==0){
				//System.out.println(3);
				return 0;
			}
		}
		b=b.pow(2);
		b=b.mod(num);
		//System.out.print(k-1+":");
		//System.out.println(b);
		if(b.compareTo(new BigInteger("-1"))!=0&&b.compareTo(temp)!=0){
			//System.out.println(4);
			return 0;
		}
		System.out.println(5);
		return .75;
	}
	
	public static int factorTwo(BigInteger num){
		int count=0;
		BigInteger test=num;
		while((test.mod(new BigInteger("2"))).compareTo(BigInteger.ZERO)==0){
			test=test.divide(new BigInteger("2"));
			count++;
		}
		System.out.println("Two power is "+count);
		return count;
	}
}
