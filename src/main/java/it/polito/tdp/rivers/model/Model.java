package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;
	private List<Flow> flows;
	private double Fmed;
	
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
		
		return first+" "+last+" "+n+" "+Fmed;
	}
	
}
