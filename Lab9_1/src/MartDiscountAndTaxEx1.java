
import java.util.*;


    class Goods
    {
        public double Price;
        public int Quantity;
        public String kind;
        public String maker;
        public double FinalPrice;
        public int FinalQuantity;
        
        Goods(double Price, int Quantity, String kind, String maker)
        {
        	this.Price = this.FinalPrice = Price;
        	this.Quantity = this.FinalQuantity = Quantity;
        	this.kind = kind;  
        	this.maker = maker;
        }

        public double GetTotal()
        {
        	return FinalPrice * FinalQuantity;
        }
        public void setPrice(CustomerInformation ci)
        {
        	if(ci.level == 0) { level0Price(); }
        	else if(ci.level == 1) { level1Price(); }
        	else if(ci.level == 2) { level2Price(); }
        	else { level3Price(ci); }
        }
        public void level0Price()
        {
        	this.FinalPrice = this.Price;
        }
        public void level1Price()
        {
        	level0Price();
        	if(this.kind.equals("apple") || this.kind.equals("Mario3"))
        	{
        		this.FinalQuantity = this.Quantity - (this.Quantity/3);
        		this.FinalPrice = 
        				this.Price * 0.9;
        	}
        }
        public void level2Price()
        {
        	level0Price();
        	level1Price();
        	if(this.kind.equals("dish") || this.kind.equals("samdasu")) 
        	{
        		this.FinalQuantity = this.Quantity - (this.Quantity/2);
        		this.FinalPrice = this.Price - 2000;
        	}
        }
        public void level3Price(CustomerInformation ci)
        {
        	level0Price();
        	level1Price();
        	level2Price();
        	if(this.kind.equals("jade") || this.kind.equals("jade-best"))
        	{
        		if(ci.member)
        		{
        			this.FinalPrice *= 0.7;
        		}
        	}
        }
    }
    
    class ShopCart
    {
        public Vector<Goods> items;
        CustomerInformation ci;

        public ShopCart(Vector<Goods> items, CustomerInformation ci) 
        {
            this.items = items;
            this.ci = ci;
        }
        
        public double GetTotal()
        {
            double cartTotal = 0;
            for(Goods item : items)
            {
                cartTotal += item.GetTotal();
            }
            return cartTotal;
        }
    }

    class CustomerInformation {
    	public String name;
    	public String card;
    	public boolean member;
    	public int level;
    	
    	CustomerInformation(String name, String card, boolean member, int level) {
    		this.name = name;
    		this.card = card;
    		this.member = member;
    		this.level = level;
    	};
    }
    
    
public class MartDiscountAndTaxEx1 {

	public static void main(String [] args) {
		
		// (카트내 상품정보)
		//
		//   상품명:"blouse",     생산자:"CJ",       단가:50000,  개수:5
		//   상품명:"apple",      생산자:"Nongshim", 단가:20000,  개수:3
		//   상품명:"dish",       생산자:"Cornell",  단가:5000,   개수:4
		//   상품명:"Mario3",     생산자:"nintendo", 단가:35000,  개수:5
		//   상품명:"samdasu",    생산자:"Hanra",    단가:4000,   개수:2
		//   상품명:"jade",       생산자:"Jin",      단가:200000, 개수:2
		//   상품명:"pearl",      생산자:"Jin",      단가:300000, 개수:2
		//   상품명:"jade-best",  생산자:"Sun",      단가:400000, 개수:2
		//   상품명:"pearl-best", 생산자:"Sun",      단가:600000, 개수:2

		
		// (상품, 카테고리) 맵
		//
		//   상품명:"blouse",     카테고리:"cloth"
		//   상품명:"apple",      카테고리:"food"
		//   상품명:"dish",       카테고리:"kitchen"
	    //   상품명:"Mario3",     카테고리:"game"
	    //   상품명:"samdasu",    카테고리:"drink"
	    //   상품명:"jade",       카테고리:"jewel"
	    //   상품명:"pearl",      카테고리:"jewel"
	    //   상품명:"jade-best",  카테고리:"jewel"
	    //   상품명:"pearl-best", 카테고리:"jewel"

		
		// (쇼핑카트 고객정보)
		//
		//   고객명:"홍길동", 결재카드:"삼성", 매장회원:true, 고객레벨:3

		
		//
		//----------------------------------------
		// (실습1) 상품 별 기본 할인정책 적용한 후의 금액 계산
		//----------------------------------------
		//
		//   1) (기본정책(t))   상품에 할인정책을 배정하지 않고 정규 가격 적용함
		//   2) (할인정책1(t1)) 상품 3개를 2개로 취급(2+1정책) + 단가를 10% 할인
		//   3) (할인정책2(t2)) 상품 2개를 1개로 취급(1+1정책) + 단가를 2천원 할인 
		//   4) (할인정책3(t3)) 매장 회원고객이면 30% 할인
		//
		//     - 상품("blouse", "pearl", "pearl-best"): 기본정책 적용
		//     - 상품("apple", "Mario3")              : 할인정책1 적용
		//     - 상품("dish", "samdasu")              : 할인정책2 적용
		//     - 상품("jade", "jade-best")            : 할인정책3 적용
		//
		
		
		// 각 상품과  해당 카테고리 맵 설정
		HashMap<String, String> cate = new HashMap<String, String>();
		cate.put("blouse", "cloth");
		cate.put("apple", "food");
		cate.put("dish", "kitchen");
		cate.put("Mario3", "game");
		cate.put("samdasu", "drink");
		cate.put("jade", "jewel");
		cate.put("pearl", "jewel");
		cate.put("jade-best", "jewel");
		cate.put("pearl-best", "jewel");
		
		// 고객정보 객체 생성
		CustomerInformation ci= new CustomerInformation("홍길동", "삼성", true, 3);
		
		
		//
		// 쇼핑카트에 실린 매장 상품(Goods) 객체 생성
		//
		
		Vector<Goods> items = new Vector<Goods>();
		items.add(new Goods(50000, 5, "blouse", "CJ"));
		items.add(new Goods(20000, 3, "apple", "Nongshim"));
		items.add(new Goods(5000, 4, "dish", "Cornell"));
		items.add(new Goods(35000, 5, "Mario3", "nintendo"));
		items.add(new Goods(4000, 2, "samdasu", "Hanra"));
		items.add(new Goods(200000, 2, "jade", "Jin"));
		items.add(new Goods(300000, 2, "pearl", "Jin"));
		items.add(new Goods(400000, 2, "jade-best", "Sun"));
		items.add(new Goods(600000, 2, "pearl-best", "Sun"));
		

		// 매장내 4가지 할인정책 객체(기본정책(t), 할인정책1(t1), 할인정책2(t2), 할인정책3(t3)) 코드 생성
		//
		// (상품 별 할인정책)
		//
		//   1) (기본정책(t))   상품에 할인정책을 배정하지 않고 정규 가격 적용함
		//   2) (할인정책1(t1)) 상품 3개를 2개로 취급(2+1정책) + 단가를 10% 할인
		//   3) (할인정책2(t2)) 상품 2개를 1개로 취급(1+1정책) + 단가를 2천원 할인 
		//   4) (할인정책3(t3)) 매장 회원고객이면 30% 할인
		//
		//     - 상품("blouse", "pearl", "pearl-best"): 기본정책 적용
		//     - 상품("apple", "Mario3")              : 할인정책1 적용
		//     - 상품("dish", "samdasu")              : 할인정책2 적용
		//     - 상품("jade", "jade-best")            : 할인정책3 적용
		//		
		


		
		////--------------------------------------------------------------------------------------
        //// <실습9_1> 할인정책을 코드로 적용하는 방법을 구현하기 위하여 프로그램의 모든 부분들을 자유롭게 추가/수정/삭제할 것
		////   단, 코드 중복성 부분들을 최대한 제거하기 위하여 공통요소들을 한장소(예: 메소드 등)로 몰아 구현할 것
		////--------------------------------------------------------------------------------------

		
		
		
		// 쇼핑카트(ShopCart) 객체 생성
		ShopCart sc = new ShopCart(items, ci);

		
		// 카트 내 상품별 금액 및 총금액 계산
		System.out.println("--------------------------------------------");
		System.out.println(" [실습9-1] 카트 상품 별 할인정책 적용 이후 전체금액 계산");
		System.out.println("--------------------------------------------\n");
		for(Goods p: items)
		{
			p.setPrice(ci);
			System.out.println(" (상품명) " + p.kind + ",  (합계) " + p.GetTotal() + "원");
		}
		System.out.println("\n (총금액) " + sc.GetTotal() + "원" + "\n");
			
	}
		
}