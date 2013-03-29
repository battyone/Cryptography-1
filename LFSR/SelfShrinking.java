package LFSR;
import java.util.StringTokenizer;

public class SelfShrinking {
	static boolean[]lfsr;
	static byte[]tap;
	SelfShrinking(boolean[]initial,byte[] t){
		lfsr=initial;
		tap=t;
	}
	
	public byte nextRand(){
		boolean output=lfsr[lfsr.length-2];
		boolean deter=lfsr[lfsr.length-1];
		boolean shift=false;
		for(int i=0;i<tap.length;i++){
			if(tap[i]==1)
				shift=shift^lfsr[i];
		}
		System.arraycopy(lfsr, 0, lfsr, 1, lfsr.length-1);
		lfsr[0]=shift;
		for(int i=0;i<tap.length;i++){
			if(tap[i]==1)
				shift=shift^lfsr[i];
		}
		System.arraycopy(lfsr, 0, lfsr, 1, lfsr.length-1);
		lfsr[0]=shift;
		if(deter){
			if(output)
				return 1;
			else
				return 0;
		}
		return nextRand();
	}
	public static void main(String[]args){
		byte[][]allTap=new byte[144][12];
		int row=0;
		String temp;
		StringTokenizer s;
		for(int i=0;i<72;i++)
			allTap[i][11]=1;
		allTap[0][10]=1;allTap[1][10]=1;allTap[2][10]=1;allTap[3][10]=1;
		allTap[0][9]=1;allTap[1][9]=1;allTap[4][9]=1;allTap[5][9]=1;
		allTap[4][8]=1;allTap[6][8]=1;allTap[7][8]=1;
		TextFileInput texts=new TextFileInput("input.txt");
		temp=texts.readLine();
		while(temp.length()!=0){
			temp=temp.substring(1, temp.length()-1);
			s=new StringTokenizer(temp, ", ");
			while(s.hasMoreTokens()){
				int curr=Integer.parseInt(s.nextToken());
				allTap[row][curr-1]=1;
				if(curr==12)
					allTap[143-row][curr-1]=1;
				else
					allTap[143-row][12-curr-1]=1;
			}
			row++;
			temp=texts.readLine();
		}
		
		
		for(int i=0;i<144;i++){
			for(int j=0;j<12;j++)
				System.out.print(allTap[i][j]);
			System.out.println();
		}
		boolean[]init=new boolean[12];
		SelfShrinking test;
		for(int i1=0;i1<2;i1++)
			for(int i2=0;i2<2;i2++)
				for(int i3=0;i3<2;i3++)
					for(int i4=0;i4<2;i4++)
						for(int i5=0;i5<2;i5++)
							for(int i6=0;i6<2;i6++)
								for(int i7=0;i7<2;i7++)
									for(int i8=0;i8<2;i8++)
										for(int i9=0;i9<2;i9++)
											for(int i10=0;i10<2;i10++)
												for(int i11=0;i11<2;i11++)
													for(int i12=0;i12<2;i12++){
														if(i1==1)
															init[0]=true;
														if(i2==1)
															init[1]=true;
														if(i3==1)
															init[2]=true;
														if(i4==1)
															init[3]=true;
														if(i5==1)
															init[4]=true;
														if(i6==1)
															init[5]=true;
														if(i7==1)
															init[6]=true;
														if(i8==1)
															init[7]=true;
														if(i9==1)
															init[8]=true;
														if(i10==1)
															init[9]=true;
														if(i11==1)
															init[10]=true;
														if(i12==1)
															init[11]=true;
													}
	}
}