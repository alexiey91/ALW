package it.uniroma2.algoritmi.web.index;

import org.jgrapht.event.ConnectedComponentTraversalEvent;

import it.unimi.dsi.logging.ProgressLogger;
import it.unimi.dsi.webgraph.ImmutableGraph;
import it.unimi.dsi.webgraph.algo.ConnectedComponents;

public class degreeCentrality {

	public degreeCentrality() {
		// TODO Auto-generated constructor stub
	}

	public double computeDegreeCentrality(ImmutableGraph graph){
		double degreeCentrality =0;
		double sum=0;
		int maxGraphDegree = maxDegree(graph);
	//	System.out.println("maxDegree:"+maxGraphDegree);
		double denominator = 0;
		//denominator= computeDegreeSubgraph(graph);
		denominator = (graph.numNodes()-1)*(graph.numNodes()-2);
		
		for (int i=0 ; i< graph.numNodes();i++){
			sum=sum + (maxGraphDegree - graph.outdegree(i));
			//System.out.println("sum in position["+i+"]= " +maxGraphDegree+"-"+graph.outdegree(i)+"="+sum);
		}
	//	System.out.println("SUM:"+sum);
	//	System.out.println("Denominator:"+denominator);
	degreeCentrality= sum/denominator;
		return degreeCentrality;
		
	}
	
	public double computeDegreeSubgraph(ImmutableGraph graph){
	
	double sum =0;
	ConnectedComponents.compute(graph, 0, null);
	//System.out.println(ConnectedComponents.compute(graph, 0, null).numberOfComponents);
	//System.out.println(ConnectedComponents.compute(graph, 0, null).component[36]);
		ImmutableGraph maximumSubgraph= ConnectedComponents.getLargestComponent(graph, 0, null);
	//System.out.println("maximumSubgraph:"+maximumSubgraph);
		int maxSubGraphDegree = maxDegree(maximumSubgraph);
	//	System.out.println("maximumSubgraph degree:"+maxSubGraphDegree);
	for ( int i=0 ; i< maximumSubgraph.numNodes();i++){
			sum = sum + (maxSubGraphDegree-maximumSubgraph.outdegree(i));
		}
	
	return sum;}
	
	public int maxDegree( ImmutableGraph graph){
		int max=0;
		for(int i =0 ; i<graph.numNodes();i++){
			if(graph.outdegree(i)>max) max = graph.outdegree(i);
		}
		return max;
	}
}
