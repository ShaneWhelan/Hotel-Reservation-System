import java.util.ArrayList;
import java.io.FileNotFoundException;
public class Hotels {
	private ArrayList<Room> threeStar = new ArrayList<Room>();
	private ArrayList<Room> fourStar =  new ArrayList<Room>();
	private ArrayList<Room> fiveStar =  new ArrayList<Room>();
	private String choice;

	/*
	Hotels Constructor creates 3 hotels by calling the method that belongs to the three of them. Giving the 2D array.
	*/
	public Hotels(String info[][]) throws FileNotFoundException{ //Use 2D array to create hotels
		create3star(info);
		create4star(info);
		create5star(info);
	}
	
	/*
	Creates 3 Star hotel using the 2D Array created from the l4hotels.csv file. Populates the ArrayList with rooms of each type.
	*/
	public void create3star(String info[][]){
		for(int i = 0; i < 3; i++){
				String roomType = info[i+9][1];
				int minOccAdult = Character.getNumericValue(info[i+9][3].charAt(0));
				int minOccChild = Character.getNumericValue(info[i+9][3].charAt(2));
				int maxOccAdult = Character.getNumericValue(info[i+9][4].charAt(0));
				int maxOccChild = Character.getNumericValue(info[i+9][4].charAt(2));
				int noOfRooms = Integer.parseInt(info[i+9][2]);
				double[] rates = new double[7];
				for(int r = 0; r < 7; r++){
					rates[r] = Double.parseDouble(info[i+9][r+5]);
				}
				for(int z = 0; z < noOfRooms; z++){
					Room rum = new Room(roomType, minOccAdult, minOccChild, maxOccAdult, maxOccChild, rates, noOfRooms);
					threeStar.add(rum);
				}
		}
	}
	
	/*
	Creates 4 Star hotel using the 2D Array created from the l4hotels.csv file. Populates the ArrayList with rooms of each type.
	*/
	public void create4star(String info[][]){
		for(int i = 0; i < 3; i++){
				String roomType = info[i+6][1];
				int minOccAdult = Character.getNumericValue(info[i+6][3].charAt(0));
				int minOccChild = Character.getNumericValue(info[i+6][3].charAt(2));
				int maxOccAdult = Character.getNumericValue(info[i+6][4].charAt(0));
				int maxOccChild = Character.getNumericValue(info[i+6][4].charAt(2));
				int noOfRooms = Integer.parseInt(info[i+6][2]);
				double[] rates = new double[7];
				for(int r = 0; r < 7; r++){
					rates[r] = Double.parseDouble(info[i+6][r+5]);
				}
				for(int z = 0; z < noOfRooms; z++){
					Room rum = new Room(roomType, minOccAdult, minOccChild, maxOccAdult, maxOccChild, rates, noOfRooms);
					fourStar.add(rum);
				}
		}
	}
	
	/*
	Creates 5 Star hotel using the 2D Array created from the l4hotels.csv file. Populates the ArrayList with rooms of each type.
	*/
	public void create5star(String info[][]){
		for(int i = 0; i < 4; i++){
				String roomType = info[i+2][1];
				int minOccAdult = Character.getNumericValue(info[i+2][3].charAt(0));
				int minOccChild = Character.getNumericValue(info[i+2][3].charAt(2));
				int maxOccAdult = Character.getNumericValue(info[i+2][4].charAt(0));
				int maxOccChild = Character.getNumericValue(info[i+2][4].charAt(2));
				int noOfRooms = Integer.parseInt(info[i+2][2]);
				double[] rates = new double[7];
				for(int r = 0; r < 7; r++){
					rates[r] = Double.parseDouble(info[i+2][r+5]);
				}
				for(int z = 0; z < noOfRooms; z++){
					Room rum = new Room(roomType, minOccAdult, minOccChild, maxOccAdult, maxOccChild, rates, noOfRooms);
					fiveStar.add(rum);
				}
		}
	}

	/*
	The current hotel is chosen by the user, the name of the hotel is then given back as a string.
	*/
	public String getArrayString(String choice){
		String hotel = "3Star";
		if(choice.equals("3")){
			hotel = "3Star";
		}else if(choice.equals("4")){
			hotel = "4Star";
		}else if(choice.equals("5")){
			hotel = "4Star";
		}
		return hotel;
	}
	
	/*
	Gets a list of the room types associated with the current hotel selected so the user can select a room.
	*/
	public ArrayList<String> getUniqueRoomTypes(ArrayList<Room> list){
		ArrayList<String> unique = new ArrayList<String>();
		unique.add(list.get(0).getRoomType());
		for(int i = 1; i < list.size(); i++){
			if(list.get(i).getRoomType() != list.get(i - 1).getRoomType()){
				unique.add(list.get(i).getRoomType());
			}
		}
		return unique;
	}
	
	public ArrayList<Room> getThreeStar() {
		return threeStar;
	}

	public ArrayList<Room> getFourStar() {
		return fourStar;
	}

	public ArrayList<Room> getFiveStar() {
		return fiveStar;
	}
	
	public ArrayList<Room> getArray(String choice){
		ArrayList<Room> room;
		room = getThreeStar();
		if(choice.equals("3")){
			room = getThreeStar();
		}else if(choice.equals("4")){
			room = getFourStar();
		}else if(choice.equals("5")){
			room = getFiveStar();
		}
		return room;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}
	
	public int getMaxAdults(String roomType){
		int max = 0; 
			for(int i = 0; i < getArray(this.choice).size(); i++){
				if((roomType.equals(getArray(this.choice).get(i).getRoomType())) == true){
						max = getArray(this.choice).get(i).getMaxOccAdult();
						return max;
				}
			}
		return max;
	}
	
	public int getMaxChildren(String roomType){
		int max = 0; 
		for(int i = 0; i < getArray(this.choice).size(); i++){
			if((roomType.equals(getArray(this.choice).get(i).getRoomType())) == true){
					max = getArray(this.choice).get(i).getMaxOccChild();
					return max;
			}
		}
		return max;
	}

	public int getMaxRooms(String roomType){
		int max = 0; 
		for(int i = 0; i < getArray(this.choice).size(); i++){
			if((roomType.equals(getArray(this.choice).get(i).getRoomType())) == true){
					max = getArray(this.choice).get(i).getMaxNoRooms();
					return max;
			}
		}
		return max;
	}
}
