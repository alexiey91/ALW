package it.uniroma2.algoritmi.web;

import it.unimi.dsi.webgraph.ImmutableGraph;
import it.unimi.dsi.webgraph.Stats;
import it.unimi.dsi.webgraph.Transform;
import it.unimi.dsi.webgraph.algo.StronglyConnectedComponents;
import it.unimi.dsi.webgraph.jung.JungAdapter;

import com.google.common.collect.Iterables;

public class Bridge  {

	//private ImmutableGraph graph;
	
	public Bridge( ) {
		
		// TODO Auto-generated constructor stub
		//this.graph = graph;
	}

//	public ImmutableGraph getGraph(){
//		return graph;
//	}
	
	public int numberOfBridge(ImmutableGraph graph)throws UnsupportedOperationException{
	
		long arcs = graph.numArcs();
		
		long node = graph.numNodes();
		long i=0;
		System.out.println("Eliminati nodi"+graph);
		JungAdapter jungGraph = new JungAdapter(graph, Transform.transpose(graph));
		System.out.println("jungGraph: "+jungGraph.toString());
		
		System.out.println("Transform.transpose(graph)="+Transform.transpose(graph));
		System.out.println("numero archi prima: "+jungGraph.getEdgeCount());
		System.out.println("numero nodi prima: "+jungGraph.getVertexCount());
		System.out.println("collezione archi: "+jungGraph.findEdge(0, 1));
		System.out.println("get out edges"+ jungGraph.getEdges());
		long test = Iterables.get(jungGraph.getEdges(), 0);
		
		System.out.println("test : "+test);
		try {
			for(int j=0 ; j<100; j++){
				if(jungGraph.containsEdge(test)==true){
			System.out.println("j="+j);

			if(jungGraph.removeEdge(test)) System.out.println("Porco mondo");
				}
			}
			System.out.println("Remove edge o" +jungGraph.removeEdge(test));
			//System.out.println("Remove node 1"+jungGraph.removeVertex(1));
		}

		    catch (Exception exc) {
		      System.out.println(exc);
		    }
		System.out.println("numero archi dopo: "+jungGraph.getEdgeCount());
		System.out.println("numero nodi dopo: "+jungGraph.getVertexCount());
		return 0;}
}
