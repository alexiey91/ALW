package it.uniroma2.algoritmi.web.utils;

import it.unimi.dsi.webgraph.ImmutableGraph;
import it.uniroma2.algoritmi.web.index.intermediationCentrality;

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
			//System.out.println("graph.successors per il nodo ["+i+"] ="+graph.successorArray(i));
			int [] x = new int[graph.successorArray(i).length];
			
			x=graph.successorArray(i);
			List<Integer> app = new ArrayList<Integer>();
			int last =x[0];
			
			app.add(x[0]);
			for(int j=0 ; j< x.length;j++){
				System.out.println("x["+j+"] ="+x[j]);
				if(j!=0 && x[j]!=last){
				last= x[j];
					app.add(x[j]);
				}
			}
			System.out.println("lista senza duplicati:"+app);
			int [] y = new int[app.size()];
			for( int j =0 ; j< app.size();j++) y[j]=app.get(j);
		//	for(int j=0 ; j< graph.successorArray(i).length;j++)System.out.println("x["+j+"] "+x[j]);
			
			
			 temp.setNode(i);
			 temp.setsuccessor(y);
			 successor.add(temp);
		}
		
		
		
		return successor;
	}
	
	public List<successorObject> listOfSuccessorSingle(ImmutableGraph graph){
		List<successorObject> successor = new ArrayList<successorObject>();
	
				
		for(int i=0 ; i< graph.numNodes();i++){
			//LazyIntIterator successors = graph.successors( i );
			 successorObject temp = new successorObject();
			//System.out.println("graph.successors per il nodo ["+i+"] ="+graph.successorArray(i));
			int [] x = new int[graph.successorArray(i).length];
			
			x=graph.successorArray(i);
			List<Integer> app = new ArrayList<Integer>();
		
			int last=0;
			if(graph.successorArray(i).length != 0){last = x[0];
			
			
			app.add(x[0]);
			for(int j=0 ; j< x.length;j++){
				System.out.println("x["+j+"] ="+x[j]);
				if(j!=0 && x[j]!=last){
				last= x[j];
					app.add(x[j]);
				}
			}
			}
			System.out.println("lista senza duplicati:"+app);
			int [] y = new int[app.size()];
			for( int j =0 ; j< app.size();j++) y[j]=app.get(j);
		//	for(int j=0 ; j< graph.successorArray(i).length;j++)System.out.println("x["+j+"] "+x[j]);
			
			
			 temp.setNode(i);
			 temp.setsuccessor(y);
			 successor.add(temp);
		}
		
		
		
		return successor;
	}
	public List<predecessorObject> listOfPredecessor(ImmutableGraph graph){
		List<predecessorObject> predecessor = new ArrayList<predecessorObject>();
		List<successorObject> successor = new ArrayList<successorObject>();
		intermediationCentrality inter = new intermediationCentrality();
		successor=inter.listOfSuccessor(graph);
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
