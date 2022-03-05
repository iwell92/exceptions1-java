package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if (!checkOut.after(checkIn)) { //A data do check-out não for posterior a data do check-in
			throw new DomainException("Check-out date must be after check-in date");
		}
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
	
	/*Não usaremos o setCheckIn não vamos deixar que as datas sejam alteradas arbitrariamente
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}*/

	public Date getCheckOut() {
		return checkOut;
	}

	/*Não usaremos o setCheckOut não vamos deixar que as datas sejam alteradas arbitrariamente
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}*/
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime(); //getTime pega as diferenças entre as datas em milissegundos
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); //convertendo milissegundos em dias
	}
	
	public void updateDates(Date checkIn, Date checkOut) {
		Date now = new Date(); //As datas para atualização não podem ser anterior a data atual
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates for update must be future dates");//<- Essa é a menssagem do e.getMessage
		}
		if (!checkOut.after(checkIn)) { //A data do check-out não for posterior a data do check-in
			throw new DomainException("Check-out date must be after check-in date");
		}
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
