import java.util.GregorianCalendar;
public class Reservation{
	private int number;
	private String name;
	private boolean resType;
	private GregorianCalendar dateFrom;
	private String dateF;
	private String dateT;
	private GregorianCalendar dateTo;
	private int numberNights;
	private int roomsNum;
	private String roomType;
	private int adults;
	private int children;
	private boolean breakfast;
	private double totalCost;
	private double deposit;
	
	/*
	Creates A new Reservation Object from user Input.
	*/
	public Reservation(String name, String dateFrom, boolean resType, int nights, int roomsNum, String roomType, int adults, int children, boolean breakfast, double deposit){
		this.number = (int) (Math.random() * 1000);
		this.name = name;
		this.dateF = dateFrom;
		String[] f = dateFrom.split("/");
		int d = Integer.parseInt(f[0]);
		int m = Integer.parseInt(f[1]) - 1;
		int y = Integer.parseInt(f[2]);
		int length = d + nights;
		this.dateT = length + "/" + (m+1) + "/" + y;
		this.dateFrom = new GregorianCalendar(y, m, d);
		this.dateTo = new GregorianCalendar(y, m, d + nights);
		this.resType = resType;
		this.numberNights = nights;
		this.roomsNum = roomsNum;
		this.roomType = roomType;
		this.adults = adults;
		this.children = children;
		this.breakfast = breakfast;
		this.deposit = deposit;
	}
	
	/*
	Creates A new Reservation Object from res.csv file. The Trick here is to get the Number from the csv file so that the user can cancel with the same number.
	*/
	public Reservation(int number, String name, String dateFrom, boolean resType, int nights, int roomsNum, String roomType, int adults, int children, boolean breakfast, double deposit){
		this.number = number;
		this.name = name;
		this.dateF = dateFrom;
		String[] f = dateFrom.split("/");
		int d = Integer.parseInt(f[0]);
		int m = Integer.parseInt(f[1]) - 1;
		int y = Integer.parseInt(f[2]);
		int length = d + nights;
		this.dateT = length + "/" + (m+1) + "/" + y;
		this.dateFrom = new GregorianCalendar(y, m, d);
		this.dateTo = new GregorianCalendar(y, m, d + nights);
		this.resType = resType;
		this.numberNights = nights;
		this.roomsNum = roomsNum;
		this.roomType = roomType;
		this.adults = adults;
		this.children = children;
		this.breakfast = breakfast;
		this.deposit = deposit;
	}
	
	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}
	
	public boolean isResType() {
		return resType;
	}

	public GregorianCalendar getDateFrom() {
		return dateFrom;
	}
	
	public GregorianCalendar getDateTo() {
		return dateTo;
	}

	public int getNumberNights() {
		return numberNights;
	}

	public int getRoomsNum() {
		return roomsNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public int getAdults() {
		return adults;
	}
	
	public int getChildren(){
		return children;
	}

	public boolean isBreakfast() {
		return breakfast;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public double getDeposit() {
		return deposit;
	}

	public String getDateF() {
		return dateF;
	}

	public String getDateT() {
		return dateT;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
}
