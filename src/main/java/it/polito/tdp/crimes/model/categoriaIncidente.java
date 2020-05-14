package it.polito.tdp.crimes.model;

public class categoriaIncidente {

	private Reato reato;
	private int incidente;
	private String quartiere;

	public categoriaIncidente(Reato reato, int incidente, String quartiere) {
		super();
		this.reato = reato;
		this.incidente = incidente;
		this.quartiere = quartiere;
	}

	public Reato getReato() {
		return reato;
	}

	public void setReato(Reato reato) {
		this.reato = reato;
	}

	public int getIncidente() {
		return incidente;
	}

	public void setIncidente(int incidente) {
		this.incidente = incidente;
	}

	public String getQuartiere() {
		return quartiere;
	}

	public void setQuartiere(String quartiere) {
		this.quartiere = quartiere;
	}

	@Override
	public String toString() {
		return "categoriaIncidente [reatp=" + reato + ", incidente=" + incidente + ", quartiere=" + quartiere
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + incidente;
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
		categoriaIncidente other = (categoriaIncidente) obj;
		if (incidente != other.incidente)
			return false;
		return true;
	}

	
}
