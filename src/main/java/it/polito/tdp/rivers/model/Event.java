package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Event implements Comparable<Event>{
	
	public enum EventType {
		NORMAL,
		TRACIMAZIONE
	}
	
	private EventType type;
	private double Fin;
	private double Fout;
	private double tracimazione;
	private LocalDate day;
	
	

	public Event(EventType type, double fin, double fout, double tracimazione, LocalDate day) {
		this.type = type;
		Fin = fin;
		Fout = fout;
		this.tracimazione = tracimazione;
		this.day = day;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public double getFin() {
		return Fin;
	}

	public void setFin(double fin) {
		Fin = fin;
	}

	public double getFout() {
		return Fout;
	}

	public void setFout(double fout) {
		Fout = fout;
	}

	public double getTracimazione() {
		return tracimazione;
	}

	public void setTracimazione(double tracimazione) {
		this.tracimazione = tracimazione;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public EventType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(Fin);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Fout);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tracimazione);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (Double.doubleToLongBits(Fin) != Double.doubleToLongBits(other.Fin))
			return false;
		if (Double.doubleToLongBits(Fout) != Double.doubleToLongBits(other.Fout))
			return false;
		if (Double.doubleToLongBits(tracimazione) != Double.doubleToLongBits(other.tracimazione))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public int compareTo(Event o) {
		return this.day.compareTo(o.getDay());
	}
}
