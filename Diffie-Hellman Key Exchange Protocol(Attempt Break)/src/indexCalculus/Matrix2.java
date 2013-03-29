package indexCalculus;

public class Matrix2 {
	private static double[][] matrix;
	
	public static void ref(double[][] m){
		int i=0,j=0;
		matrix=new double[m.length][m[0].length];//create a copy of the matrix
		for(int x=0;x<m.length;x++)
			for(int y=0;y<m[0].length;y++)
				matrix[x][y]=m[x][y];//to prevent data change
		while(i<matrix.length){
			//System.out.println(i+" "+j);
			if(i==j){//if at main diagonal
				if(matrix[i][j]==0){//if the main diagonal is 0
					int tempi=i;
					while(tempi<matrix.length&&matrix[tempi][j]==0)//find a row with that column not 
						tempi++;//equal to zero
					if(tempi!=matrix.length){
						double[]tempRow=matrix[i];//swap rows
						matrix[i]=matrix[tempi];
						matrix[tempi]=tempRow;
					}
				}
				if(matrix[i][j]!=1&&matrix[i][j]!=0){//if the main diagonal is not greater than 1 or less than 0
					double divide=matrix[i][j];
					for(int x=0;x<matrix[i].length;x++)
						matrix[i][x]=matrix[i][x]/divide;//divide the whole row by the value of main diagonal
				}
			}
			if(i!=j&&matrix[i][j]!=0){//if not in main diagonal and not equal to 0
				double multiplier=matrix[i][j];
				if(matrix[j][j]!=0){
					for(int x=0;x<matrix[j].length;x++)
						matrix[i][x]=matrix[i][x]-matrix[j][x]*multiplier;//subtract previous row that is same place as column
				}
				else{
					double[]tempRow=matrix[j];
					matrix[j]=matrix[i];
					matrix[i]=tempRow;
					i=j;
					j--;
				}
			}
			j++;
			if(j>=matrix[0].length||j>i){
				i++;
				j=0;
			}
		}
	}
	
	public static void rref(double[][] m){
		ref(m);
		for(int k=0;k<matrix.length;k++){
			for(int w=0;w<matrix[0].length;w++)
					System.out.print(matrix[k][w]+" ");
			System.out.println();
		}
		System.out.println();
		//code
		int i=matrix.length-2;
		int j=0;
		if(matrix.length>matrix[0].length){
			j=matrix[0].length-1;
		}
		else
			j=matrix.length-1;
		while(i>=0){
			if(matrix[i][j]!=0&&j<matrix.length){
				double multiplier=matrix[i][j];
				if(matrix[j][j]==1){
					for(int x=i;x<matrix[0].length;x++){
							matrix[i][x]=matrix[i][x]-matrix[j][x]*multiplier;
					}
				}
			}
			j--;
			if(j<=i){
				if(matrix.length>matrix[0].length)
					j=matrix[0].length-1;
				else
					j=matrix.length-1;
				i--;
			}
		}
		//end code
		for(int k=0;k<matrix.length;k++){
			for(int w=0;w<matrix[0].length;w++)
					System.out.print(matrix[k][w]+" ");
			System.out.println();
		}
	}
}
