package it.polito.tdp.crimes.model;

public class Reato {

	private String tipo;
	private int codice; 
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Reato(String tipo, int codice) {
		super();
		this.tipo = tipo;
		this.codice=codice;
		
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice;
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
		Reato other = (Reato) obj;
		if (codice != other.codice)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reato [tipo=" + tipo + ", codice=" + codice + "]";
	}

	
	
}
