package discreteLog;
import java.math.BigInteger;
public class Matrix {

   
	/* Gaussian elimination to solve AL=b mod p however, i am doing [Ab]= matrix
	 * --> matrix*L mod p and solve for L<--*/
	public static BigInteger[] GaussianEliminationModP(BigInteger[][] A,BigInteger[] b,BigInteger n) {
		int maxCol = b.length, maxPivot = 0;

		// Gaussian elimination
		for (int pivot = 0; pivot < maxCol; pivot++) {

			// find pivot row
			maxPivot = pivot;
			for (int i = pivot + 1; i < maxCol; i++) {
				if (A[i][pivot].abs().gcd(n).equals(BigInteger.ONE))
					maxPivot = i;
				//System.out.println("finding pivot");
				//printBoard(A);
			}

			// Swap matrix "A" pivot row with maxPivot row
			swap(A, b, pivot, maxPivot);

			//if (A[pivot][pivot].abs().equals(BigInteger.ZERO))
				//throw new RuntimeException("Matrix is singular");

			// pivot within A mod p
			for (int i = pivot + 1; i < maxCol; i++) {
				//System.out.println("a mod p=" + pivot + " " + pivot);
				//printBoard(A);
				BigInteger alpha = BigIntegerMath.lnr(A[i][pivot].multiply(A[pivot][pivot].modInverse(n)), n);
				
				b[i]= BigIntegerMath.lnr(b[i].subtract( (alpha.multiply(b[pivot]) )), n);

				for (int j = pivot; j < maxCol; j++)
					A[i][j] = BigIntegerMath.lnr(A[i][j].subtract((alpha.multiply(A[pivot][j]))), n);
			}
		}// End of outer for-loop

		// back substitution
		BigInteger[] x = new BigInteger[maxCol];
		for (int i = maxCol - 1; i >= 0; i--) {
			BigInteger sum = BigInteger.ZERO;
			for (int j = i + 1; j < maxCol; j++) {
				sum = sum.add((A[i][j].multiply(x[j])));
			}
			//System.out.println("backwords=" + i);
			//printBoard(A);
			x[i]= BigIntegerMath.lnr((b[i].subtract(sum)).multiply((A[i][i].modInverse(n))), n);
		}
		
		printBoard(A);
		for (int it = 0; it < b.length; it++) {
            System.out.println("end x= "+ x[it]);
        }
	

		return x;

	}// End of GaussianEliminationModP

	// Swap the pivot row with maxPivot
	private static void swap(BigInteger[][] A,BigInteger[] b, int i, int j) {
		BigInteger[] temp = A[i]; A[i] = A[j]; A[j] = temp;
        BigInteger   t    = b[i]; b[i] = b[j]; b[j] = t;
	}
	//print array nothing special here
	public static void printArray(int array[]){
		for(int i =0; i<array.length-1;i++)System.out.print(array[i]+", ");
		System.out.println(array[array.length-1]);System.out.println();
	}
	
	// printing the matrix board
	public static void printBoard(BigInteger matrix[][]) {
		for (int i = 0; i < matrix.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("| ");
		}
		System.out.println("");
	}



}