package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}
	
	/*N�o usaremos o setCheckIn n�o vamos deixar que as datas sejam alteradas arbitrariamente
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}*/

	public Date getCheckOut() {
		return checkOut;
	}

	/*N�o usaremos o setCheckOut n�o vamos deixar que as datas sejam alteradas arbitrariamente
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}*/
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime(); //getTime pega as diferen�as entre as datas em milissegundos
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); //convertendo milissegundos em dias
	}
	
	public void updateDates(Date checkIn, Date checkOut) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}
	
}
