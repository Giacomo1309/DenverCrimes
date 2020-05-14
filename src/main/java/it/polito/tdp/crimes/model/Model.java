package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {

	/*
	 * for(incidente i: incidenti) { i.getQuartiere; verifico se in quel quartiere
	 * siano successi altri tipi di incidenti}
	 * 
	 * 
	 * 
	 * 
	 * verifico se in quel quartiere siano successi altri tipi di
	 * incidenti(quartiere,tipodiIncidente) for(incidente i: incidenti)
	 * if(i.getQuartiere = quartiere) if(i.tipoDiIncidente!= tipodiIncidente)
	 * if(nella mappa gia è presente quell'arco, aumento il peso altrimenti creo
	 * l'arco) { aggiungo alla mappa (Vertice,vertice,peso=1) } alla fine il peso
	 * sarà fatto diviso due senno faccio andata e ritorno e aumento !
	 */
	private SimpleGraph<Reato, DefaultWeightedEdge> grafo;
	private Map<Integer, Reato> idMap;
	private EventsDao dao;

	public Model() {
		idMap = new HashMap<Integer, Reato>();
		dao = new EventsDao();

	}

	public Map<Integer, Reato> getVertici() {
		return this.idMap;
	}

	public void creaGrafo(int mese, String categoria) {
		this.dao.riempioIdMap(idMap, categoria, mese);// riempio la mappa dei vertici
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, this.idMap.values());
		List<categoriaIncidente> list = new ArrayList<categoriaIncidente>(dao.get(this.idMap, categoria, mese));
		for (categoriaIncidente i : list) {

			riempiArchi(i, list);
		}

		// aggiungo gli archi (UNA LISTA)
		// for (Border b : dao.getArchi(anno, this.idMap)) {
		// this.grafo.addEdge(b.getStato1(), b.getStato2());
		// non so se devo aggiungere anche da stato2 a stato1
		// }
	}

	public void riempiArchi(categoriaIncidente i, List<categoriaIncidente> list) {

		for (categoriaIncidente x : list) {

			if (i.getIncidente() != x.getIncidente() && i.getQuartiere().compareTo(x.getQuartiere()) == 0
					&& i.getReato().getCodice() != x.getReato().getCodice()) {
				if (this.grafo.containsEdge(i.getReato(), x.getReato())) {
					DefaultWeightedEdge e = this.grafo.getEdge(i.getReato(), x.getReato());
					this.grafo.setEdgeWeight(e, this.grafo.getEdgeWeight(e) + 0.5);
				} else {
				}
				Graphs.addEdge(this.grafo, i.getReato(), x.getReato(), 0.5);

			}
		}
//		for (DefaultWeightedEdge e : this.grafo.edgeSet()) {
	//		this.grafo.setEdgeWeight(e, this.grafo.getEdgeWeight(e) / 2);
	//	}
	}

	public SimpleGraph<Reato, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	
	
}
