package LFSR;

public class testClass {
	public static void main(String[]args){
		byte[] rand=new byte[1000000];
		int[] count=new int[2];
		count[0]=0;count[1]=0;
		randomness test=new randomness();
		
		for(int i=0;i<1000000;i++)
			rand[i]=test.nextRand();
		for(int i=0;i<1000000;i++)
			count[rand[i]]++;
		for(int i=0;i<1000000;i++){
			if(i%1000==0)
				System.out.println();
			System.out.print(rand[i]);
		}
		System.out.println();
		System.out.println(count[0]+" "+count[1]);
	}
}
