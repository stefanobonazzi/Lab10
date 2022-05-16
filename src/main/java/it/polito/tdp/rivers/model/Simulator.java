package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.model.Event.EventType;

public class Simulator {

	//Coda degli eventi
	private PriorityQueue<Event> queue;
	
	//Parametri di simulazione
	private double Q;
	private double C;
	private double Fout_min;
	
	//Output della simulazione
	private int ggNonGarantiti;
	private double Cmed;
	
	//stato del mondo simulato
	List<Flow> flow;
	
	public Simulator(List<Flow> flow) {
		this.flow = flow;
	}

	public void initialize(double Fmed, double k) {
		this.Q = k*Fmed*(30*86400);
		this.C = this.Q/2;
		this.Fout_min = 0.8*Fmed;
		this.queue = new PriorityQueue<Event>();
		
		this.queue.add(new Event());
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}
	}

	private void processEvent(Event e) {
		switch (e.getType()) {
		case FINMAGGFOUT:
			
			break;

		case TRACIMAZIONE:
			
			break;
		}
	}
}
