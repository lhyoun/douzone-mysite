package com.douzone.mysite.paging;

public class Page {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int total;
	// private Criteria cri;

	private int pageNum;
	private int amount = 10;
	private int realEnd;

	// @Autowired
	public Page(int pageNum, int total) {
		this.pageNum = pageNum;
		this.total = total;

		// Math.ceil() 함수는 주어진 숫자보다 크거나 같은 숫자 중 가장 작은 숫자를 integer 로 반환합니다.
		this.endPage = (int) (Math.ceil(pageNum * 0.1)) * 10;
		// <(현재페이지[1]/페이지당 개수[10])=[0.1]>올림=[1]*10=[10]
		// <(현재페이지[2]/페이지당 개수[10])=[0.2]>올림=[1]*10=[10]
		// <(현재페이지[10]/페이지당 개수[10])=[1]>올림=[1]*10=[10]
		// <(현재페이지[11]/페이지당 개수[10])=[1.1]>올림=[2]*10=[10]
		// 1~10 => 10, 11~20 => 20
		// 버튼 10개 중 젤 마지막 번호를 나타냄

		this.startPage = this.endPage - (this.amount - 1);
		// 10-(10-1)=1
		// 20-(10-1)=1
		// 버튼 10개 중 첫 번째 번호를 나타냄

		// System.out.println("**DTO** endpage: "+this.endPage);
		// System.out.println("**DTO** startpage: "+this.startPage);

		// 마지막 페이지 번호
		this.realEnd = (int) (Math.ceil((total * 1.0) / this.amount));
		// 올림<(133/10)=[13.3]>=[14]
		// 마지막 페이지가 14페이지

		// if (realEnd <= this.endPage)
		// endPage = realEnd;

		this.prev = this.startPage > 1;
		// 현재 페이지가 5페이지면 start 페이지가 1이고 end가 10이다

		this.next = this.endPage < realEnd;
		// end페이지가 realeEnd페이지보다 작아야 다음으로 넘길 페이지가 있으니 True를 반환
		// 그게 아니면 다음으로 넘길 페이지가 없으니까 False
	}

	public int getNum() {
		// 1 page => 0
		// 2 page => 10
		return (this.pageNum - 1) * this.amount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getRealEnd() {
		return realEnd;
	}

	public void setRealEnd(int realEnd) {
		this.realEnd = realEnd;
	}

	@Override
	public String toString() {
		return "Page [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", pageNum=" + pageNum + ", amount=" + amount + ", realEnd=" + realEnd + "]";
	}

}