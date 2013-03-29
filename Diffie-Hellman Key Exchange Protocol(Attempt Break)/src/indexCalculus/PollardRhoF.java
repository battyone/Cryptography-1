package indexCalculus;

import java.math.BigInteger;

public class PollardRhoF {
	
	public static BigInteger ZERO = BigInteger.valueOf(0);
	public static BigInteger TWO = BigInteger.valueOf(2);
	public static BigInteger ONE = BigInteger.valueOf(1);

	public BigInteger[] factoring(BigInteger n){

		BigInteger y = BigInteger.valueOf((int) (Math.random() * 100))
				.multiply(n).divide(new BigInteger("100"));
		BigInteger m = BigInteger.valueOf((int) (Math.random() * 100))
				.multiply(n).divide(new BigInteger("100"));
		BigInteger r = ONE, q = ONE;
		BigInteger x, ys, gcd, p;
		BigInteger pq[]={ZERO,ZERO};

		do {
			x = y;
			// System.out.println("what are u x "+ x);
			for (BigInteger i = ONE; i.compareTo(r) < 0; i = i.add(ONE)) {
				y = f(y,n);
				// System.out.println("first loop "+ y);
			}
			BigInteger k = ZERO;

			do {
				ys = y;
				// System.out.println("what are ys "+ ys);
				for (BigInteger i = ONE; i.compareTo(m.min(r.subtract(k))) < 0; i = i
						.add(ONE)) {
					y = f(y, n);
					q = q.multiply((x.subtract(y)).abs()).mod(n);
					// System.out.println("what r u q "+ q);
				}
				gcd = q.gcd(n);
				// System.out.println("what is the gcd of q and n "+ g);
				k = k.add(m);
			}while (k.compareTo(r) < 0 && gcd.compareTo(ONE) < 1);

			r = r.multiply(TWO);
		} while (gcd.compareTo(ONE) < 1);

		if (gcd.equals(n)) {
			do {
				ys = f(ys, n);
				gcd = (x.subtract(ys)).abs().gcd(n);
			} while (gcd.compareTo(ONE) < 1);
		}
		
		p=n.divide(gcd);
		pq[0]=gcd.min(p);pq[1]=gcd.max(p);	// put them in order
		return pq;
	}

	/** f(x)=x^2 +1 mod n */
	private BigInteger f(BigInteger x,BigInteger n) {
		return x.pow(2).add(BigInteger.ONE).mod(n);
	}
}
