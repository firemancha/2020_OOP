public class Practice3_2 {
	public static void main (String[] args) {
		int intArray[][] = new int[4][4];

		
		// 2차원 배열의 모든 원소를 0으로 초기화 
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				intArray[i][j] = 0;
			}
		}
		
		// 배열의 9개 랜덤위치에 랜덤한 정수 생성하여 저장
		
		int count = 0;
		while(count < 9)
		{
			int row = (int)(Math.random()*4);
			int col = (int)(Math.random()*4);
			
			if(intArray[row][col] == 0)
			{
				intArray[row][col] = (int)(Math.random()*9 + 1);
				count++;
			}
		}
		
		// 2차원 배열 출력
		System.out.println("---- Random Matrix ----");
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				System.out.print(intArray[i][j] + "  ");
			}
			System.out.println(" ");
		}		
	}
}
