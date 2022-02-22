package servlets;

public class SubMatrixThread extends Thread {
	private String[] matriceA;
	private String[] matriceB;
	private int start;
	private String matriceResult;
	
	
	public SubMatrixThread(String[] matriceA, String[] matriceB, int start) {
		super();
		this.matriceA = matriceA;
		this.matriceB = matriceB;
		this.start = start;
	}


	@Override
	public void run() {
		super.run();
		this.matriceResult = sottraiMatrici(this.matriceA, this.matriceB, this.start);
		
	}

	private String sottraiMatrici(String[] matriceA, String[] matriceB, int start) {
		String result = "";
		for (int i = 0; i < 2; i++) {
			result += " " + (Float.parseFloat(matriceA[i+start]) - Float.parseFloat(matriceB[i+start]));
		}
		return result.trim();
	}


	public String getMatriceResult() {
		return matriceResult;
	}
}
