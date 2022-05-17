package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;
import java.util.SplittableRandom;

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
		ggNonGarantiti = 0;
		Cmed = C;
	}
	
	public void caricaEventi() {
		for(Flow f: flow) {
			double Fout = this.Fout_min;
			SplittableRandom random = new SplittableRandom();
			boolean irrigation = random.nextInt(1, 101) <= 5;
			
			if(irrigation) {
				Fout *= 10;
			}
			this.queue.add(new Event(EventType.NORMAL, f.getFlow(), Fout, 0, f.getDay()));
		}
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}
		
		System.out.println("Media: "+(Cmed/flow.size())+"\nGiorni non garantiti: "+ggNonGarantiti);
	}

	private void processEvent(Event e) {
		switch (e.getType()) {
		case NORMAL:
			if(e.getFin()>e.getFout()) {
				double aggiunta = (e.getFin()-e.getFout())*86400;
				if((C+aggiunta) > Q) {
					this.queue.add(new Event(EventType.TRACIMAZIONE, 0, 0, (C+aggiunta)-Q, e.getDay()));
					C = Q;
					Cmed += C;
				} else {
					C = C+aggiunta;
					Cmed += C;
				}
			} else {
				if((C-((e.getFout()-e.getFin())*86400)) < (Fout_min*86400)) {
					ggNonGarantiti++;
				} 
				C = C-((e.getFout()-e.getFin())*86400);
				if(C <= 0)
					C =0;
				
				Cmed += C;
			}
			
			break;

		case TRACIMAZIONE:
			
			break;
		}
	}

	public int getGgNonGarantiti() {
		return ggNonGarantiti;
	}

	public double getCmed() {
		return (Cmed/flow.size())/1000000;
	}
	
}
