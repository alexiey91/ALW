package it.uniroma2.algoritmi.web.index;

import it.unimi.dsi.webgraph.ImmutableGraph;

public class clusteringIndex {

	public clusteringIndex() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	/**
	 * Calcolo l'indice di centralità massimo del grafo.
	 * */
	
	public static double clusterIndex(ImmutableGraph graph){
		double clusterIndex=0;
		for(int i=0 ; i< graph.numNodes();i++){
			if(clusterIndex< clusterCoefficent(graph, i))clusterIndex= clusterCoefficent(graph, i);
		}
		
	return clusterIndex;}
	
	/**
	 * Calcolo il coefficente di Cluster per il singolo Nodo sfruttando la seguente formula:
	 * Numero di link appartentente al vertice per il singolo Nodo / ki*(ki-1)
	 * ki = numero dei vertici vicini al nodo scelto
	 * */
	
	public static double clusterCoefficent(ImmutableGraph graph , int node){
		double clusterCoefficent=0;
		
		int k = graph.successorArray(node).length;
		
		if(k > 1){
			int []N = new int[k];
			N=graph.successorArray(node);
			int neighboursLinks = 0;
			
			for(int  i=0 ; i< N.length;i++){
				int []neighbourSuccessors= new int [graph.successorArray(N[i]).length];
				neighbourSuccessors= graph.successorArray(N[i]);
				for(int j=0 ; j<neighbourSuccessors.length;j++){
					for( int l=0 ; l< N.length;l++){
						if(neighbourSuccessors[j]== N[l]){
							neighboursLinks++;
						}
					}
				}
			}
			
			clusterCoefficent= (double) neighboursLinks/(k*(k-1));
		}
		
		
	return clusterCoefficent;}
	
	
	
}
