package it.polito.tdp.crimes.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		int mese = 8 ;
		model.creaGrafo(8, "murder");
		Reato r1 = new Reato("homicide-family",902);
		Reato r2 = new Reato("homicide-other", 912) ;
	//	System.out.println(model.getVertici());
		for(int i =0; i<model.getGrafo().edgeSet().size(); i++) {
			
		System.out.println(model.getGrafo().getEdgeWeight(model.getGrafo().getEdge(r1, r2)));
		}
	}

}
