package it.polito.tdp.rivers.model;

public class Event {
	
	public enum EventType {
		FINMAGGFOUT,
		TRACIMAZIONE
	}
	
	private EventType type;
	private double Fin;
	
	
	public EventType getType() {
		return type;
	}
}
