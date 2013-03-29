package indexCalculus;

import java.math.BigInteger;

public class Main {
	public static void main(String[]args){
		int square=8000;//(int) (Math.random()*10);
		double[][] matrix={{3,0,1,2,0,346},{6,0,0,1,1,171},{0,3,1,0,1,153},{0,4,1,0,0,442},{3,5,1,0,0,458}};
		/*for(int i=0;i<matrix.length;i++)
			for(int j=0;j<matrix[0].length;j++)
				matrix[i][j]=(int)(Math.random()*100);*/
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++)
				System.out.print(matrix[i][j]+" ");
			System.out.println();
		}
		System.out.println();
		//Matrix.rref(matrix);
		int[]factorBase={2,3,7,11,13};
		BigInteger s=new BigInteger("1");//BigInteger.ZERO;
		BigInteger base=new BigInteger("5");
		BigInteger h=new BigInteger("5872");
		BigInteger modulo=new BigInteger("14087");
		BigInteger beta=base.modPow(s, modulo);
		//beta=beta.multiply(h);
		while(Factor.factorWithBase(beta,factorBase)==null){
			System.out.println(s+" "+beta);
			//System.out.println();
			s=s.add(BigInteger.ONE);
			beta=base.modPow(s, modulo);
			//beta=beta.multiply(h);
		}
		int[]exponent=Factor.factorWithBase(beta,factorBase);
		for(int i=0;i<exponent.length;i++)	
			System.out.print(exponent[i]);
		System.out.println();
	}
}
