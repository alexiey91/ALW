package it.uniroma2.algoritmi.web.index;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.BellmanFordShortestPath;
import org.jgrapht.graph.DefaultEdge;

public class wienerIndex {

	public wienerIndex() {
		// TODO Auto-generated constructor stub
	}

	public List<Double> computeWienerIndex(DirectedGraph<String, DefaultEdge> grafo, int numNodes){
		List<Double>index = new ArrayList<Double>();
	System.out.println("numNodews"+numNodes);
		double W_graph= computeWienerIndexGraph(grafo);
		double counter=0;
		for(int i=0 ; i< grafo.vertexSet().size();i++){
			double W_node = computeWienerSingleNode(grafo, i);
			System.out.println("W_graph "+W_graph+" W_nodes "+W_node);
			if(numNodes %2 !=0){
				System.out.println("DIspari");
				double fn = ((5.0/24.0)*Math.pow(numNodes,3))-((3.0/4.0)*Math.pow(numNodes,2))+((19.0/24.0)*numNodes)-(1.0/4.0);
				System.out.println("fn:"+fn);
				counter = (numNodes*W_node-W_graph)/fn;
			}
			else{
				System.out.println("pari");
				double fn = (5.0/24.0)*Math.pow(numNodes, 3)-(3.0/4.0)*Math.pow(numNodes,2)+(2.0/3.0)*numNodes;
				counter = (numNodes*W_node-W_graph)/fn;
			}
			index.add(counter);
		}
		
	return index;}
	
	
	/**
	 * Calcola l'indice di Wiener tra tutti i nodi*/
	public double computeWienerSingleNode(DirectedGraph<String, DefaultEdge> grafo, int pos){
		double counter= 0.0;
		
		for(int i=0 ; i< grafo.vertexSet().size();i++){
			
			if(i!=pos){
				BellmanFordShortestPath path= new BellmanFordShortestPath(grafo,
						grafo.vertexSet().toArray()[pos],
                grafo.edgeSet().size(),
                0.85);
		
			//	System.out.println("costo per il nodo"+pos+"="+path.getCost(grafo.vertexSet().toArray()[i]));
		counter= counter+path.getCost(grafo.vertexSet().toArray()[i]);
		}
				
			
		}
		
		return counter;
	}
	
	
	/**
	 * Questa funzione  calcola l'indice di Wiener di tutto l'interno grafo --> somme su tutte le coppie di nodi di tutti
	 * gli shorthest path per coppie di nodi*/
	
	public double computeWienerIndexGraph(DirectedGraph<String, DefaultEdge> grafo){
		double counter=0;
		for(int i=0 ; i< grafo.vertexSet().size();i++){
			for(int j=0 ; j<grafo.vertexSet().size();j++){
				if(i!=j){
				BellmanFordShortestPath path= new BellmanFordShortestPath(grafo,
						grafo.vertexSet().toArray()[i],
                grafo.edgeSet().size(),
                0.85);
		
			//	System.out.println("costo per il nodo"+i+"="+path.getCost(grafo.vertexSet().toArray()[j]));
		counter= counter+path.getCost(grafo.vertexSet().toArray()[j]);
		}
			}
		}
		return counter;
	}
	
	
	
}
