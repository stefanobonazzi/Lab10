package it.polito.tdp.rivers.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
	
		model.gestisciDati(new River(2, "Vatnsdalsa River"));
		model.simula(0.5);
	}

}
