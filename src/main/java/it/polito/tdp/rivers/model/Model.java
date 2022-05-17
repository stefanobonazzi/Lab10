package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;
	private List<Flow> flows;
	private double Fmed;
	private double Cmed;
	private int ggNonGarantiti;
	
	public Model () {
		this.dao = new RiversDAO();
	}
	
	public List<River> getAllRivers() {
		return dao.getAllRivers();
	}

	public String gestisciDati(River river) {
		flows = dao.getFlows(river);
		Collections.sort(flows);
		
		LocalDate first = flows.get(0).getDay();
		LocalDate last = flows.get(flows.size()-1).getDay();
		int n = flows.size();
		
		for(Flow f: flows) {
			Fmed += f.getFlow();
		}
		Fmed = Fmed/n;
		System.out.println(first+"\n"+last+"\n"+n+"\n"+Fmed);
		return first+" "+last+" "+n+" "+Fmed;
	}
	
	public void simula(double k) {
		Simulator simulator = new Simulator(flows);
		simulator.initialize(Fmed, k);
		simulator.caricaEventi();
		simulator.run();
		
		this.Cmed = simulator.getCmed();
		this.ggNonGarantiti = simulator.getGgNonGarantiti();
	}

	public double getCmed() {
		return Cmed;
	}

	public int getGgNonGarantiti() {
		return ggNonGarantiti;
	}
	
}
