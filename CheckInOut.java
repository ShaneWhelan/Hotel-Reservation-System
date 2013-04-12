import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CheckInOut {
	private ArrayList<Reservation> checkedIn = new ArrayList<Reservation>();
	private ArrayList<Reservation> checkedOut = new ArrayList<Reservation>();
	private ArrayList<Reservation> cancellations = new ArrayList<Reservation>();
	
	public CheckInOut(String[][] checkInData, String[][] checkOutData, String[][] cancellations, Hotels hot){
		createCheckedIn(checkInData, hot);
		createCheckedOut(checkOutData, hot);
		createCancellations(cancellations, hot);
	}
	
	public void createCheckedIn(String[][] checkInData, Hotels hot){
		ArrayList<Room> rum;
		for(int i = 1; i< checkInData.length; i++){
			int number = Integer.parseInt(checkInData[i][0]);
			int count = 0;
			for(int z = 0; z< checkedIn.size(); z++){
				if(number == checkedIn.get(z).getNumber()){
					count++;
				}
			}
			if(count == 0){
				if(checkInData[i][7].equals("Classic Single") || checkInData[i][7].equals("Classic Twin") || checkInData[i][7].equals("Classic Double")){
					rum = hot.getArray("3");
				}else if(checkInData[i][7].equals("Executie Double") || checkInData[i][7].equals("Executive Twin") || checkInData[i][7].equals("Executive Single")){
					rum = hot.getArray("4");
				}else{
					rum = hot.getArray("5");
				}
				
				String name = checkInData[i][1];
				boolean resType = Boolean.parseBoolean(checkInData[i][2]);
				String dateFrom = checkInData[i][3];
				int nights = Integer.parseInt(checkInData[i][5]);
				int rooms = Integer.parseInt(checkInData[i][6]);
				String roomType = checkInData[i][7];
				int adults = Integer.parseInt(checkInData[i][8]);
				int children = Integer.parseInt(checkInData[i][9]);
				boolean breakfast = Boolean.parseBoolean(checkInData[i][10]);
				double deposit = Double.parseDouble(checkInData[i][11]);
				
				Reservation res = new Reservation(number, name, dateFrom, resType, nights, rooms, roomType, adults, children, breakfast, deposit);
				int loop = 1;
				for(int j = 0; j < rum.size(); j++){
					if((roomType.equals(rum.get(j).getRoomType())) == true){
						if(loop <= rooms){
							rum.get(j).getBooked().add(res);
							loop++;
						}else{
							checkedIn.add(res);
							break;
						}
					}
				}
			}
		}
	}
	
	public void createCheckedOut(String[][] checkOutData, Hotels hot){
		ArrayList<Room> rum;
		for(int i = 1; i< checkOutData.length; i++){
			int number = Integer.parseInt(checkOutData[i][0]);
			int count = 0;
			for(int z = 0; z< checkedOut.size(); z++){
				if(number == checkedOut.get(z).getNumber()){
					count++;
				}
			}
			if(count == 0){
				if(checkOutData[i][7].equals("Classic Single") || checkOutData[i][7].equals("Classic Twin") || checkOutData[i][7].equals("Classic Double")){
					rum = hot.getArray("3");
				}else if(checkOutData[i][7].equals("Executie Double") || checkOutData[i][7].equals("Executive Twin") || checkOutData[i][7].equals("Executive Single")){
					rum = hot.getArray("4");
				}else{
					rum = hot.getArray("5");
				}
				
				String name = checkOutData[i][1];
				boolean resType = Boolean.parseBoolean(checkOutData[i][2]);
				String dateFrom = checkOutData[i][3];
				int nights = Integer.parseInt(checkOutData[i][5]);
				int rooms = Integer.parseInt(checkOutData[i][6]);
				String roomType = checkOutData[i][7];
				int adults = Integer.parseInt(checkOutData[i][8]);
				int children = Integer.parseInt(checkOutData[i][9]);
				boolean breakfast = Boolean.parseBoolean(checkOutData[i][10]);
				double deposit = Double.parseDouble(checkOutData[i][11]);
				
				Reservation res = new Reservation(number, name, dateFrom, resType, nights, rooms, roomType, adults, children, breakfast, deposit);
				int loop = 1;
				double sevenYears = 220898664000.0;
				GregorianCalendar today = new GregorianCalendar();
				if((today.getTimeInMillis() - res.getDateTo().getTimeInMillis()) < sevenYears){
					for(int j = 0; j < rum.size(); j++){
						if((roomType.equals(rum.get(j).getRoomType())) == true){
							if(loop <= rooms){
								rum.get(j).getBooked().add(res);
								loop++;
							}else{
								checkedOut.add(res);
								break;
							}
						}
					}
				}
			}
		}
	}
	
	public void createCancellations(String[][] cancellationsData, Hotels hot){
		ArrayList<Room> rum;
		for(int i = 1; i< cancellationsData.length; i++){
			int number = Integer.parseInt(cancellationsData[i][0]);
			int count = 0;
			for(int z = 0; z< cancellations.size(); z++){
				if(number == cancellations.get(z).getNumber()){
					count++;
				}
			}
			if(count == 0){
				if(cancellationsData[i][7].equals("Classic Single") || cancellationsData[i][7].equals("Classic Twin") || cancellationsData[i][7].equals("Classic Double")){
					rum = hot.getArray("3");
				}else if(cancellationsData[i][7].equals("Executie Double") || cancellationsData[i][7].equals("Executive Twin") || cancellationsData[i][7].equals("Executive Single")){
					rum = hot.getArray("4");
				}else{
					rum = hot.getArray("5");
				}
				
				String name = cancellationsData[i][1];
				boolean resType = Boolean.parseBoolean(cancellationsData[i][2]);
				String dateFrom = cancellationsData[i][3];
				int nights = Integer.parseInt(cancellationsData[i][5]);
				int rooms = Integer.parseInt(cancellationsData[i][6]);
				String roomType = cancellationsData[i][7];
				int adults = Integer.parseInt(cancellationsData[i][8]);
				int children = Integer.parseInt(cancellationsData[i][9]);
				boolean breakfast = Boolean.parseBoolean(cancellationsData[i][10]);
				double deposit = Double.parseDouble(cancellationsData[i][11]);
				
				Reservation res = new Reservation(number, name, dateFrom, resType, nights, rooms, roomType, adults, children, breakfast, deposit);
				int loop = 1;
				double sevenYears = 220898664000.0;
				GregorianCalendar today = new GregorianCalendar();
				if((today.getTimeInMillis() - res.getDateTo().getTimeInMillis()) < sevenYears){
					for(int j = 0; j < rum.size(); j++){
						if((roomType.equals(rum.get(j).getRoomType())) == true){
							if(loop <= rooms){
								rum.get(j).getBooked().add(res);
								loop++;
							}else{
								cancellations.add(res);
								break;
							}
						}
					}
				}
			}
		}
	}
	
	public boolean checkIn(int num, ReservationData data){
		GregorianCalendar today = new GregorianCalendar();
		for(int i = 0; i < data.getList().size(); i++){
			if(data.getList().get(i).getNumber() == num){
				if(data.getList().get(i).getDateFrom().after(today)){

				}else{
					checkedIn.add(data.getList().get(i));
					data.getList().remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkOut(int num){

		for(int i = 0; i < this.checkedIn.size(); i++){
			if(this.checkedIn.get(i).getNumber() == num){
					checkedOut.add(this.checkedIn.get(i));
					checkedIn.remove(i);
					return true;
			}
		}
		return false;
	}

	public ArrayList<Reservation> getCheckedIn(){
		return checkedIn;
	}

	public ArrayList<Reservation> getCheckedOut() {
		return checkedOut;
	}

	public ArrayList<Reservation> getCancellations() {
		return cancellations;
	}
}