package beans;

public class Matrici extends Thread {
	private int [] matA;
	private int [] matB;
	public int[] getMatA() {
		return matA;
	}

	public int[] getMatB() {
		return matB;
	}

	public Matrici(int[] matA, int[] matB) {
		super();
		this.matA = matA;
		this.matB = matB;
	}
	
	
	public int [] subtract() {
		int result[] = new int[matA.length];
		
		for(int i = 0; i<matA.length; i++) {
			result[i] = matA[i] - matB[i];
		}
		
		return result;
		
	}
}