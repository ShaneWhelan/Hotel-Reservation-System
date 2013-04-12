import java.util.ArrayList;

public class Room{
	private String roomType;
	private int minOccAdult;
	private int minOccChild;
	private int maxOccAdult;
	private int maxOccChild;
	private double[] rates = new double[7];
	private int maxNoRooms;
	private ArrayList<Reservation> booked = new ArrayList<Reservation>();
	
	/*
	Creates a room object details of which are extracted from the csv elsewhere.
	*/
	public Room(String roomType, int minOccAdult, int minOccChild, int maxOccAdult, int maxOccChild, double[] rates, int maxNoRooms) {
		this.roomType = roomType;
		this.minOccAdult = minOccAdult;
		this.minOccChild = minOccChild;
		this.maxOccAdult = maxOccAdult;
		this.maxOccChild = maxOccChild;
		this.rates = rates;
		this.maxNoRooms = maxNoRooms;
	}

	public ArrayList<Reservation> getBooked() {
		return booked;
	}

	public String getRoomType() {
		return roomType;
	}

	public int getMinOccAdult() {
		return minOccAdult;
	}

	public int getMinOccChild() {
		return minOccChild;
	}

	public int getMaxOccAdult() {
		return maxOccAdult;
	}

	public int getMaxOccChild() {
		return maxOccChild;
	}

	public double[] getRates() {
		return rates;
	}


	public int getMaxNoRooms() {
		return maxNoRooms;
	}
}