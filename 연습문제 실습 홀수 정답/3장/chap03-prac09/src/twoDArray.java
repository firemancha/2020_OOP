public class twoDArray {
	public static void main (String[] args) {
		int intArray[][] = new int[4][4];
		
		for (int i=0; i<intArray.length; i++)
			for (int j=0; j<intArray[i].length; j++)
				intArray[i][j] = (int)(Math.random()*10 + 1); // 1부터 10사이의 임의의 수 생성하여 저장
		
		for (int i=0; i<intArray.length; i++) {
			for (int j=0; j<intArray[i].length; j++)
				System.out.print(intArray[i][j] + "\t");
			System.out.println();
		}
	}
}
