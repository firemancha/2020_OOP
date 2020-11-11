import java.util.*;

    interface SalesLogicForSummation<T>
    {
    	double sum(T p);
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
    
    class DiscountCoupon {
    	String kind;
    	double discount;
    	double rate;
    	
        DiscountCoupon(String kind, double discount, double rate) {
        	this.kind = kind;
        	this.discount = discount;
        	this.rate = rate;
        }
    }
    
    class  SalesPromotion {
    	boolean vip;
    	boolean membercard;
    	public double threshold;
    	public double discount;
    	
    	SalesPromotion(boolean vip, boolean membercard, double threshold, double discount) {
    		this.vip = vip;
    		this.membercard = membercard;
    		this.threshold = threshold;
    		this.discount = discount;
    	}
    }
    
    class TaxInformation {
    	public double basicRate;
    	public HashMap<String, Double> rate;
    	TaxInformation(double basicRate, HashMap<String, Double> rate) {
    		this.basicRate = basicRate;
    		this.rate = rate;
    	}
    	double getRate(String cate) {
    		Double d = rate.get(cate);
    		return d == null ? basicRate : d;
    	}
    }

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
        public double AdjustPrice(SalesLogicForSummation<ShopCart> isp) {  // delete method itself
        	return isp.sum(this);
        }
       
    }

    
public class MartDiscountAndTaxEx2 {

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

		
		// (쇼핑카트 고객정보)
		//
		//   고객명:"홍길동", 결재카드:"삼성", 매장회원:true, 고객레벨:3

		
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

		
		// (고객이 넘겨준 2개의 쿠폰을 저장한 객체 생성)
		//
		//     - "samdasu": 개당 500원 할인
		//     - "Mario3": 개당 5000원 할인한 금액에 40% 추가할인
		//

		
		// (매장별 판매 촉진 객체 생성)
		//
		//   할인대상: VIP고객(level 3 이상) 혹은 매장전용카드("삼성")를 결재카드로 사용시
		//   할인금액: 100만원 이상 결재시 20만원 할인
		//

		
		// (세금 정보 객체 생성)
		//
		//   일반 상품                      : 부가세 10%
		//   음식물("food")     : 면세
		//   게임관련제품("game") : 특수소비세 30%
		//   보석류("jewel")    : 사치상품세 40%
		//


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

		
		//
		//--------------------------------------------------------------------------
		// (실습2) 특별 할인정책 및 세금을 적용하는 경우, 적용대상 상품들에 대한 할인정책 적용 후 총금액 계산
		//--------------------------------------------------------------------------
		//
		// (특별 할인정책 및 세금 적용) (생산자, 카드결재사, 매장팀)별 판매촉진, 쿠폰, 본사 정기세일, 매장별 판매촉진, 세금계산
		//
		//   5) (매장팀 판매촉진) 상품1팀("food", "drink" 담당) 팀 세일, 담당 상품 전체금액 3만원 초과시 10% 할인
		//   6) (생산자 판매촉진) 카트에 실린 상품 중 보석가공업체 ("Jin")의 상품 가격 합계가 50만원 이상이면 40% 할인, 이하이면 20% 할인
		//   7) (카드사 판매촉진) 삼성카드로 결재시 전체금액의 20% 할인
		//   8) (보석류 판매촉진) 카트에 실린 상품 중 카테고리가 보석("jewel")에 해당되는 상품 전체에 대하여 10% 할인
		//   9) (상품 쿠폰) 고객이 제시한 쿠폰들에 대하여 해당 제품 할인
		//                  - 상품("samdasu")쿠폰: 개당 500원 할인
		//                  - 상품("Mario3")쿠폰: 개당 5000원 할인한 금액에 대해 40% 추가할인
		//  10) (본사 정기세일) 전체금액이 50만원 넘으면 4만원 할인
		//  11) (매장별 판매 촉진) VIP고객(level 3 이상) 혹은 매장 전용 카드(삼성카드) 결재사용자 --> 100만원 이상 결재시 20만원 할인
		//  12) (세금계산) 일반 상품(부가세 10%), 음식물류(면세), 게임관련제품(특수소비세:30%), 보석류(고가제품세:40%)
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
		
		// 매장내 4가지 할인정책 객체(기본정책(t), 할인정책1(t1), 할인정책2(t2), 할인정책3(t3)) 생성
				
		// 쇼핑카트에 실린 매장 상품(Goods) 객체 생성 및 할인정책 배정
		//
		// (상품 별 할인정책 배정)
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
		Vector<Goods> items = new Vector<Goods>();
		items.add(new Goods(50000, 5, "blouse", "CJ"));  // 9개 상품 생성자의 마지막 인수 제거
		items.add(new Goods(20000, 3, "apple", "Nongshim"));
		items.add(new Goods(5000, 4, "dish", "Cornell"));
		items.add(new Goods(35000, 5, "Mario3", "nintendo"));
		items.add(new Goods(4000, 2, "samdasu", "Hanra"));
		items.add(new Goods(200000, 2, "jade", "Jin"));
		items.add(new Goods(300000, 2, "pearl", "Jin"));
		items.add(new Goods(400000, 2, "jade-best", "Sun"));
		items.add(new Goods(600000, 2, "pearl-best", "Sun"));
		
		// 쇼핑카트(ShopCart) 객체 생성
		ShopCart sc = new ShopCart(items, ci);

		// 카트 내 상품별 금액 및 총금액 계산
		System.out.println("------------------------------------------");
		System.out.println(" [실습9-1] 카트 상품 기초할인 적용 이후 전체금액 계산");
		System.out.println("------------------------------------------\n");
		for(Goods p: items)
		{
			p.setPrice(ci);
			System.out.println(" (상품명) " + p.kind + ",  (합계) " + p.GetTotal() +  "원");
			//System.out.println("debugging, Quantity:" + p.FinalQuantity + "\tPrice: " + p.FinalPrice);
		}
		System.out.println("\n (총금액) " + sc.GetTotal() + "원" + "\n");
			

		
		System.out.println("\n-----------------------------------------");
		System.out.println(" [실습9-2] 특별할인정책/세율 적용 이후 전체금액 계산");
		System.out.println("-----------------------------------------\n");

		//
		// 5) (매장팀 판매촉진) 상품1팀("food", "drink" 담당) 팀 세일, 담당 상품 전체금액 3만원 초과시 10% 할인
		//
		
		SalesLogicForSummation<ShopCart> isp = s -> { double r = 0;
			for(Goods p : s.items) 
				if(cate.get(p.kind) == "food" || cate.get(p.kind) == "drink")
					r += p.FinalPrice * p.FinalQuantity;
			return (r >= 30000) ? r * 0.9 : r;
		};
		System.out.println(" (매장팀 판매촉진 할인) \t" + sc.AdjustPrice(isp) + "원");
		
		
		//
		// 6) (생산자 판매촉진 할인) 카트에 실린 상품 중 보석가공업체 ("Jin")의 상품 가격 합계가 50만원 이상이면 40% 할인, 이하이면 20% 할인
		//
		
		isp = s -> {
			double r = 0;
			for(Goods p : s.items)
			{
				if(p.maker.equals("Jin"))
					r += p.GetTotal();
			}
			return (r >= 500000) ? r * 0.6 : r * 0.8;
		};
		System.out.println(" (생산자 판매촉진 할인) \t" + sc.AdjustPrice(isp) + "원");
		
		
		//
		// 7) (카드사 판매촉진 할인) 삼성카드로 결재시 전체금액의 20% 할인
		//
		
		isp = s -> { 
			return ci.card.equals("삼성") ? s.GetTotal() * 0.8 : s.GetTotal();
		};
		System.out.println(" (카드사 판매촉진 할인) \t" + sc.AdjustPrice(isp) +"원");
	
		
		//
		// 8) (보석류 판매촉진 할인) 카트에 실린 상품 중 카테고리가 보석("jewel")에 해당되는 상품 전체에 대하여 10% 할인
		//
		
		isp = s -> {
			double r = 0;
			for(Goods p : items)
			{
				if(cate.get(p.kind)== "jewel" )
					r += p.GetTotal();
			}
			return r * 0.9;
		};
		System.out.println(" (보석류 판매촉진 할인) \t" + sc.AdjustPrice(isp) + "원");

		
		//
		// 9) (상품 할인 쿠폰) 고객이 제시한 쿠폰들에 대하여 해당 제품 할인
		//                  - 상품("samdasu")쿠폰: 개당 500원 할인
		//                  - 상품("Mario3")쿠폰: 개당 5000원 할인한 금액에 대해 40% 추가할인
		//
		
		// 쿠폰 컬렉션 객체(cp) 생성
		HashMap<String, DiscountCoupon> cp = new HashMap<String, DiscountCoupon>();
		cp.put("samdasu", new DiscountCoupon("samdasu", 500.0, 0.0));
		cp.put("Mario3", new DiscountCoupon("Mario3", 5000, 0.4));
		
        isp = s -> {
        	double r = 0;
        	for(Goods p : items)
        	{
        		if(cp.containsKey(p.kind))
        			r += (1 - cp.get(p.kind).rate) * (p.FinalPrice - cp.get(p.kind).discount) * p.FinalQuantity;
        	}
        	return r;
        };
		System.out.println(" (상품 할인 쿠폰 적용) \t" + sc.AdjustPrice(isp) +"원");

		
		//
		// 10) (본사 정기세일) 전체금액이 50만원 이상이면 4만원 할인
		//
		
		isp = s -> {
			return s.GetTotal() > 500000 ? s.GetTotal() - 40000 : s.GetTotal();
		};
		System.out.println(" (본사 정기세일 적용) \t" + sc.AdjustPrice(isp) + "원");

		
		//
		// 11) (매장별 판매촉진 할인) VIP고객(level 3 이상) 혹은 매장 전용 카드(삼성카드) 결재사용자 --> 100만원 이상 결재시 20만원 할인
		//
		
		// 판매촉진정책 객체(sp) 생성
		SalesPromotion sp = new SalesPromotion(true, true, 1000000.0, 200000.0);
		
		isp = s -> {
			if((s.ci.level >= 3 && sp.vip) || (s.ci.card.equals("삼성") && sp.membercard))
				return s.GetTotal() >= sp.threshold ? s.GetTotal() - sp.discount : s.GetTotal();
			return s.GetTotal();
		};
		System.out.println(" (매장별 판매촉진 할인) \t" + sc.AdjustPrice(isp) +"원");

		
		//
		// 12) (세율 적용 이후) 일반 상품(부가세 10%), 음식물류(면세), 게임관련제품(특수소비세:30%), 보석류(고가제품소비세:40%)
		//
		
		// 세금 객체(tax) 생성
		HashMap<String, Double> tax = new HashMap<String, Double>();
		tax.put("jewel", 0.4);
		tax.put("food", 0.0);
		tax.put("game", 0.3);
		TaxInformation ti = new TaxInformation(0.1, tax);
		
		isp = s -> {
			double r = 0;
			for(Goods p : s.items) {
				r += p.GetTotal() * (1+ti.getRate(cate.get(p.kind)));
			}
			return r;
		};
		System.out.println(" (세율 적용 후 총액) \t\t" + sc.AdjustPrice(isp) + "원");
	}
		
}