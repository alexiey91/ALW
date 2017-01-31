package it.uniroma2.algoritmi.web;

import it.unimi.dsi.webgraph.ImmutableGraph;

import java.util.ArrayList;
import java.util.List;

public class Utils {

	public Utils() {
		// TODO Auto-generated constructor stub
	}

	
	public List<successorObject> listOfSuccessor(ImmutableGraph graph){
		List<successorObject> successor = new ArrayList<successorObject>();
	
				
		for(int i=0 ; i< graph.numNodes();i++){
			//LazyIntIterator successors = graph.successors( i );
			 successorObject temp = new successorObject();
			System.out.println("graph.successors per il nodo ["+i+"] ="+graph.successorArray(i));
			int [] x = new int[graph.successorArray(i).length];
			x=graph.successorArray(i);
			for(int j=0 ; j< graph.successorArray(i).length;j++)System.out.println("x["+j+"] "+x[j]);

			
			 temp.setNode(i);
			 temp.setsuccessor(x);
			 successor.add(temp);
		}
		
		
		
		return successor;
	}
	
	public List<predecessorObject> listOfPredecessor(ImmutableGraph graph){
		List<predecessorObject> predecessor = new ArrayList<predecessorObject>();
		List<successorObject> successor = new ArrayList<successorObject>();
		intermediationCentrality inter = new intermediationCentrality();
		successor=inter.listOfSuccessor(graph);
		System.out.println("primo for");
		for ( int i=0 ; i< successor.size();i++){
			
			predecessorObject temp = new predecessorObject();
			temp.setNode(successor.get(i).getNode());
			List<Integer> x = new ArrayList<Integer>();
			for (int j=0; j< successor.size();j++){

				
				
				for(int k=0 ; k<successor.get(j).getsuccessor().length;k++){
				//	System.out.println("i:"+i+"!="+j+" "+successor.get(j).getsuccessor()[k]+"=="+successor.get(i).getNode());
					
					if(i!=j && successor.get(j).getsuccessor()[k] == successor.get(i).getNode()){
					
				//	System.out.println("Dentro IF matchato il nodo: "+successor.get(j).getNode());
					x.add(successor.get(j).getNode());
				//	System.out.println("x["+k+"]="+x);
					
					
				}
					
					temp.setPredecessor(x);
				}
				
			}
			predecessor.add(temp);
		}
		
		
		
		return predecessor;
	}
	

}
