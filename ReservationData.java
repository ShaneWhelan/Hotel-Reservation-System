import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ReservationData{
	private ArrayList<Reservation> list = new ArrayList<Reservation>();
	
	public ReservationData(){
	}
	
	/*
	Populates the List of reservations from the res.csv file.
	*/
	public ReservationData(String[][] resData, Hotels hot){
		ArrayList<Room> rum;
		for(int i = 1; i< resData.length; i++){
			int number = Integer.parseInt(resData[i][0]);
			int count = 0;
			for(int z = 0; z< list.size(); z++){
				if(number == list.get(z).getNumber()){
					count++;
				}
			}
			if(count == 0){
				if(resData[i][7].equals("Classic Single") || resData[i][7].equals("Classic Twin") || resData[i][7].equals("Classic Double")){
					rum = hot.getArray("3");
				}else if(resData[i][7].equals("Executie Double") || resData[i][7].equals("Executive Twin") || resData[i][7].equals("Executive Single")){
					rum = hot.getArray("4");
				}else{
					rum = hot.getArray("5");
				}
				
				String name = resData[i][1];
				boolean resType = Boolean.parseBoolean(resData[i][2]);
				String dateFrom = resData[i][3];
				int nights = Integer.parseInt(resData[i][5]);
				int rooms = Integer.parseInt(resData[i][6]);
				String roomType = resData[i][7];
				int adults = Integer.parseInt(resData[i][8]);
				int children = Integer.parseInt(resData[i][9]);
				boolean breakfast = Boolean.parseBoolean(resData[i][10]);
				double deposit = Double.parseDouble(resData[i][11]);
				
				Reservation res = new Reservation(number, name, dateFrom, resType, nights, rooms, roomType, adults, children, breakfast, deposit);
				int loop = 1;
				for(int j = 0; j < rum.size(); j++){
					if((roomType.equals(rum.get(j).getRoomType())) == true){
						if(loop <= rooms){
							rum.get(j).getBooked().add(res);
							loop++;
						}else{
							list.add(res);
							break;
						}
					}
				}
			}
		}
	}
	
	/*
	Checks if a room is available and adds a reservation object to both the list of reservations and to the rooms associated with the object. 
	*/
	public boolean checkAvailability(Reservation res, Hotels hot, GregorianCalendar dateFrom,  GregorianCalendar dateTo, String roomType){// Get rid of SString parameter
		GregorianCalendar from = null;
		GregorianCalendar to = null;
		int pos = 0;
		for(int i = 0; i < hot.getArray(hot.getChoice()).size(); i++){
			if((roomType.equals(hot.getArray(hot.getChoice()).get(i).getRoomType())) == true){
				if(hot.getArray(hot.getChoice()).get(i).getBooked().size() > 0){
					for(int j = 0; j < hot.getArray(hot.getChoice()).get(i).getBooked().size(); j++){
						from = hot.getArray(hot.getChoice()).get(i).getBooked().get(j).getDateFrom();
						to = hot.getArray(hot.getChoice()).get(i).getBooked().get(j).getDateTo();
						if((dateFrom.before(from) && dateTo.before(from)) || (dateFrom.after(to) && dateTo.after(to))){
							if(hot.getArray(hot.getChoice()).get(i).getBooked().size() == j+1){
									pos = i;
									hot.getArray(hot.getChoice()).get(pos).getBooked().add(res);
									return true;
							}
						}else{
							break;
						}
					}
				}else{
					hot.getArray(hot.getChoice()).get(i).getBooked().add(res);
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	Gets the rates for the roomType associated with the Reservation object passed in.
	*/
	public double[] getRates(Reservation res, Hotels hot){
		for(int i = 0; i < hot.getArray(hot.getChoice()).size(); i++){
			if((res.getRoomType().equals(hot.getArray(hot.getChoice()).get(i).getRoomType())) == true){
				return hot.getArray(hot.getChoice()).get(i).getRates();
			}
		}
		return null;
	}
	
	/*
	Finds a Reservation and cancels it removes it from the Reservation List and removes it from the rooms associates it. Doesn't write to files.
	*/
	public boolean findRoom(int num, Hotels hot){
		GregorianCalendar greg = new GregorianCalendar();
		for(int i = 0; i < hot.getArray(hot.getChoice()).size(); i++){
			for(int j = 0; j < hot.getArray(hot.getChoice()).get(i).getBooked().size(); j++){
				if(num == hot.getArray(hot.getChoice()).get(i).getBooked().get(j).getNumber()){
					if(hot.getArray(hot.getChoice()).get(i).getBooked().get(j).isResType()){
						if((hot.getArray(hot.getChoice()).get(i).getBooked().get(j).getDateFrom().getTimeInMillis() - greg.getTimeInMillis()) > 172800000 ){
							hot.getArray(hot.getChoice()).get(i).getBooked().remove(j);
						}else{
							System.out.println("Too close to checkin date to cancel!");
							return false;
						}
					}else{
						System.out.println("Not allowed cancel AP reservations!");
						return false;
					}
				}
			}
		}
		
		
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getNumber() == num){
				if((list.get(i).getDateFrom().getTimeInMillis() - greg.getTimeInMillis()) > 172800000 ){
					list.remove(i);
				}
			}
		}
		return true;
	}
	/*
	Finds a Reservation and removes it from the Reservation List and removes it from the rooms associates it. Adds it to the cancellation list Then write to csv files.
	*/
	public boolean cancellations(int num, Hotels hot, Mainsys mainsys){
		GregorianCalendar greg = new GregorianCalendar();
		boolean flip = false;
		Reservation cancelled = null;
		for(int i = 0; i < hot.getArray(hot.getChoice()).size(); i++){
			for(int j = 0; j < hot.getArray(hot.getChoice()).get(i).getBooked().size(); j++){
				if(num == hot.getArray(hot.getChoice()).get(i).getBooked().get(j).getNumber()){
					if(hot.getArray(hot.getChoice()).get(i).getBooked().get(j).isResType()){
						if((hot.getArray(hot.getChoice()).get(i).getBooked().get(j).getDateFrom().getTimeInMillis() - greg.getTimeInMillis()) > 172800000 ){
							cancelled = hot.getArray(hot.getChoice()).get(i).getBooked().get(j);
							hot.getArray(hot.getChoice()).get(i).getBooked().remove(j);
							flip = true;

						}else{
							System.out.println("Too close to checkin date to cancel!");
							return false;
						}
					}else{
						System.out.println("Not allowed cancel AP reservations!");
						return false;
					}
				}
			}
		}
		
		if(flip == false){
			return false;
		}
		mainsys.getCheckData().getCancellations().add(cancelled);
		mainsys.getRead().writeCancellations(hot, mainsys.getCheckData());
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getNumber() == num){
				if((list.get(i).getDateFrom().getTimeInMillis() - greg.getTimeInMillis()) > 172800000 ){
					list.remove(i);
				}
			}
		}
		return true;
	}
	
	public ArrayList<Reservation> getList() {
		return list;
	}
}
