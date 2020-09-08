
public class RandomTen {

	public static void main(String[] args) {
		int n [] = new int [10]; // 배열 생성
		
		for(int i=0; i<n.length; i++) { // 10개의 랜덤한 정수 생성 및 저장
			int r = (int)(Math.random()*10 + 1);
			n[i] = r;
		}
		
		int sum = 0;
		for(int i=0; i<n.length; i++) // 합 구하기
			sum += n[i];
		
		System.out.print("랜덤한 정수들 : ");
		for(int i=0; i<n.length; i++)
			System.out.print(n[i] +" ");
		
		System.out.println("\n평균은 " + (double)sum/n.length);
	}

}
