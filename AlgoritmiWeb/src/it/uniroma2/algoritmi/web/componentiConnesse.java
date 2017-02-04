package it.uniroma2.algoritmi.web;

import it.unimi.dsi.logging.ProgressLogger;
import it.unimi.dsi.webgraph.ArcListASCIIGraph;
import it.unimi.dsi.webgraph.ImmutableGraph;
import it.unimi.dsi.webgraph.Stats;
import it.unimi.dsi.webgraph.algo.ConnectedComponents;
import it.unimi.dsi.webgraph.algo.NeighbourhoodFunction;
import it.unimi.dsi.webgraph.algo.ParallelBreadthFirstVisit;
import it.unimi.dsi.webgraph.algo.StronglyConnectedComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class componentiConnesse {

	public static void main(String arg[]) throws IOException ,UnsupportedOperationException, InterruptedException{
		// TODO Auto-generated method stub
		
		
		double archi, nodi,densità;

		ImmutableGraph graph1 = ArcListASCIIGraph.loadOffline(arg[0]);
			archi=graph1.numArcs();
			nodi =graph1.numNodes();
			
			
			System.out.println("ciao :"+graph1.toString());
			System.out.println("Numero nodi di un grafo: "+nodi);
			

			densità = archi/(nodi*(nodi-1));
		System.out.println("Dimensione di un grafo: "+ archi);
		System.out.println("Densità di un grafo orientato:"+densità );
		//System.out.println(graph1.outdegree(0));
		 System.out.println("Numero Componenti Connesse di un grafo: "+ConnectedComponents.compute(graph1, 0, new ProgressLogger()).numberOfComponents);
		 System.out.println("Numero Componenti fortemente connesse: "+ StronglyConnectedComponents.compute(graph1, false, new ProgressLogger()).numberOfComponents);
		 
		 
		 double sum=0,average;
		 List<Integer> temp = new ArrayList<Integer>();
		 for(int i = 0; i<nodi ;i++){
			temp.add(graph1.outdegree(i));
			sum= sum+graph1.outdegree(i); 
		 }
		 average = sum/nodi;
		 System.out.println("Numero medio grado nodo: "+ average);
		 System.out.println("Massimo: "+ Collections.max(temp));
		 System.out.println("Minimo:"+ Collections.min(temp));
		 System.out.println("lista"+temp);
		 
		 
		 ParallelBreadthFirstVisit grafo = new ParallelBreadthFirstVisit(graph1, 0,false,null);
		 for (int i=0 ; i< nodi ; i++){
			 System.out.println("i="+i+" nodi visitati: "+grafo.visit(i));

		 }
		
		
	//	 List<Double> ListOfneighbourdhood = new ArrayList<Double>();
	//	 neighbourhood prova = new neighbourhood();
	//	 ListOfneighbourdhood = prova.computeNeighbour(graph1, 10);
	//	 System.out.println("Lista vicinanza: "+ListOfneighbourdhood);
//		 diameter d = new diameter();
//		 double diameter = d.effectiveDiamete(0.9, ListOfneighbourdhood);
//		 System.out.println("effective diameter: "+ diameter);
//		 NeighbourhoodFunction n = new NeighbourhoodFunction();
//		long [] x = n.computeExact(graph1, 0, new ProgressLogger());
//		for ( int i=0 ; i< x.length;i++)
//		 System.out.println("x["+i+"]: "+x[i] );
//		double  diametro =n.effectiveDiameter(0.9, n.compute(graph1,0,new ProgressLogger()));
//		System.out.println("DIametro:"+diametro);
//		
//		
//	closeness closeness = new closeness();
//	
//	System.out.println("Set Of Closeness: "+closeness.computeCloseness(graph1));
//		
//	betweenness between = new betweenness();
//		System.out.println("List of betweenness: "+ between.computeBetweenness(graph1));
//		System.out.println("List of normalized betweenness: "+between.normalize(between.computeBetweenness(graph1),nodi));
//	
//		//System.out.println("elenco:"+ConnectedComponents.getLargestComponent(graph1, 0, new ProgressLogger()));
//		degreeCentrality degreecent = new degreeCentrality();
//		
//		System.out.println("Degree Centrality of graph:"+ degreecent.computeDegreeCentrality(graph1));
	
//		intermediationCentrality inter = new intermediationCentrality();
//  	inter.computeIntermediationCentrality(graph1);
//		System.out.println("MAX="+inter.manageIntermediationCentrality(inter.computeIntermediationCentrality(graph1)));
	
	
	/*pageRankCentrality page = new pageRankCentrality();
	
	System.out.println("Indice di autovettore:"+page.getPageRankIntex(page.computePageRankCentrality(graph1)));*/
	
//  clusteringIndex cluster = new clusteringIndex();
//	for(int i=0 ; i<graph1.numNodes();i++)
//	System.out.println("Per il nodo:"+i+" il valore di Clustering è pari a :"+cluster.clusterCoefficent(graph1, i));
		
//	System.out.println("L'indice di clustering è pari a:"+cluster.clusterIndex(graph1));
	
	
		embeddednessIndex embe = new embeddednessIndex();
		
		System.out.println("embe:"+embe.computeEmbeddedness(graph1));
	
		
	 dispersionCentrality disp = new dispersionCentrality();
	 System.out.println("disp:" +disp.dispersionCentrality(graph1));
	}
	
	

}
