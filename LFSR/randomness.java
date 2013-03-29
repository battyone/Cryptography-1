package LFSR;

public class randomness {
	private static boolean[]lfsr1=new boolean[19];//tap 19,18,17,13,11,10
	private static boolean[]lfsr2=new boolean[31];//tap 31,30,29,28
	private static boolean[]lfsr3=new boolean[20];//tap 20,18,17,13,11,7,5,3
	private static int clockLoc1,clockLoc2,clockLoc3;
	
	public randomness(){
		clockLoc1=10;clockLoc2=15;clockLoc3=7;
		long temp=System.currentTimeMillis()%100;
		while(temp<11){
			temp=System.currentTimeMillis()%100;
			temp*=System.currentTimeMillis()%100;temp*=System.currentTimeMillis()%100;
			temp*=System.currentTimeMillis()%100;temp*=System.currentTimeMillis()%100;
			temp*=System.currentTimeMillis()%100;temp*=System.currentTimeMillis()%100;
			temp*=System.currentTimeMillis()%100;temp*=System.currentTimeMillis()%100;
			temp*=System.currentTimeMillis()%100;temp*=System.currentTimeMillis()%100;
			temp*=System.currentTimeMillis()%100;temp*=System.currentTimeMillis()%100;
			temp*=System.currentTimeMillis()%100;temp*=System.currentTimeMillis()%100;
			temp*=System.currentTimeMillis()%100;temp*=System.currentTimeMillis()%100;
			temp*=System.currentTimeMillis()%100;temp*=System.currentTimeMillis()%100;
			temp*=System.currentTimeMillis()%100;
		}
		System.out.println(temp);
		if(temp<0)
			temp*=-1;
		System.out.println(temp);
		for(int i=0;i<lfsr1.length;i++){
			lfsr1[i]=(temp%2)==1;
			temp/=10;
		}
		for(int i=0;i<lfsr2.length;i++){
			lfsr2[i]=lfsr1[i%(lfsr1.length)]^lfsr1[(i+1)%lfsr1.length];
		}
		for(int i=0;i<lfsr3.length;i++){
			lfsr3[i]=lfsr2[i%(lfsr2.length)]^lfsr2[(i+1)%lfsr2.length];
		}
		for(int i=0;i<lfsr1.length;i++)
			if(lfsr1[i])
				System.out.print(1);
			else
				System.out.print(0);
		System.out.println();
		for(int i=0;i<lfsr2.length;i++)
			if(lfsr2[i])
				System.out.print(1);
			else
				System.out.print(0);
		System.out.println();
		for(int i=0;i<lfsr3.length;i++)
			if(lfsr3[i])
				System.out.print(1);
			else
				System.out.print(0);
		System.out.println();System.out.println();
	}
	
	public byte nextRand(){
		boolean temp1=lfsr1[9]^lfsr1[10]^lfsr1[12]^lfsr1[16]^lfsr1[17]^lfsr1[18];
		boolean temp2=lfsr2[27]^lfsr2[28]^lfsr2[29]^lfsr2[30];
		boolean temp3=lfsr3[2]^lfsr3[4]^lfsr3[6]^lfsr3[10]^lfsr3[12]^lfsr3[16]^lfsr3[17]^lfsr3[19];
		boolean output=temp1^temp2^temp3;
		System.arraycopy(lfsr1, 0, lfsr1, 1, lfsr1.length-1);
		System.arraycopy(lfsr2, 0, lfsr2, 1, lfsr2.length-1);
		System.arraycopy(lfsr3, 0, lfsr3, 1, lfsr3.length-1);
		
		/*for(int i=0;i<lfsr2.length;i++)
			if(lfsr2[i])
				System.out.print(1);
			else
				System.out.print(0);
		System.out.println();
		for(int i=0;i<lfsr2.length;i++)
			if(lfsr2[i])
				System.out.print(1);
			else
				System.out.print(0);
		System.out.println();
		for(int i=0;i<lfsr3.length;i++)
			if(lfsr3[i])
				System.out.print(1);
			else
				System.out.print(0);
		System.out.println();System.out.println();*/
		
		lfsr1[0]=temp1;
		lfsr2[0]=temp2;
		lfsr3[0]=temp3;
		if(lfsr1[clockLoc1]&&lfsr2[clockLoc2]||lfsr3[clockLoc3]&&lfsr2[clockLoc2]||lfsr1[clockLoc1]&&lfsr3[clockLoc3]){
			if(output)
				return 1;
			else
				return 0;
		}
		else
			return nextRand();
	}
}
