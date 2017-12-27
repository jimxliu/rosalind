import java.util.Scanner;

/*
Given: Two positive integers k (k≤7) and N (N≤2k). In this problem, we begin with Tom, who in the 0th generation has genotype AaBb. Tom has two children in the 1st generation, each of whom has two children, and so on. Each organism always mates with an organism having genotype AaBb.

Return: The probability that at least N AaBb organisms will belong to the k-th generation of Tom's family tree (don't count the AaBb mates at each level). Assume that Mendel's second law holds for the factors.
*/

public class IndependentAlleles {
	/* Get the probability of at least n AaBb genotypes in the kth generation
	In each generation: each organism is 1/4 likely to be AaBb, and 3/4 not. (From observation)
	For kth generation, there will be 2^k organisms, the probability that at least n organisms have AaBb genotype is the sum of the probabilities of n, n+1,..., 2^k organisms having AaBb genotype.
	The number of possible AaBb genotype combination is nCr, in which n is the total number of organisms in kth generation, 2^k, and r is the number of AaBb genotypes in that generation.
	*/
	public double getIndependentAlleles(int k, int n){
		int population = (int) Math.pow(2.0,k*1.0);
		double pr = 0;
		for(int i = n; i <= population; i++){
		    pr += combination(population,i) * Math.pow(0.25,i) * Math.pow(0.75,population - i);
		}
		return pr;
	}

	// Recursively calculate the combination, nCr
	public long combination(int n, int r){
		if( r == 0 || n == r){
			return 1;
		} else {
			return combination(n-1,r) + combination(n-1,r-1);
		}
	}

	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter k:");
		int k = sc.nextInt();
		System.out.print("Enter n:");
		int n = sc.nextInt();
		IndependentAlleles ia = new IndependentAlleles();
		double pr = ia.getIndependentAlleles(k,n);
		System.out.format("k: %d, n: %d, pr: %.3f\n",k,n,pr);
	}
}
