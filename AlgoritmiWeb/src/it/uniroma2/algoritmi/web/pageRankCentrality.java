package it.uniroma2.algoritmi.web;

import it.unimi.dsi.webgraph.ImmutableGraph;

import java.util.ArrayList;
import java.util.List;

public class pageRankCentrality {

	public pageRankCentrality() {
		// TODO Auto-generated constructor stub
	}

	
	public List<Double> computePageRankCentrality(ImmutableGraph graph){
	
		List<successorObject> succ = new ArrayList<successorObject>();
		intermediationCentrality inter = new intermediationCentrality();
		succ= inter.listOfSuccessor(graph);
		List<predecessorObject> pred = new ArrayList<predecessorObject>();
		pred=listOfPredecessor(graph);
		for(int i=0 ; i< pred.size();i++)
		System.out.println("predeccessori del nodo"+pred.get(i).getNode()+"sono: "+pred.get(i).getPredecessor());
		//Passo 1 imposto tutte le pagine ad un valore iniziale 1 - d/ N dove d= 0.85 
		List<Double> initialRank = new ArrayList<Double>();
		double d = 0.85;
		for(int i=0 ; i<graph.numNodes();i++)
		initialRank.add(((1.0-d)/graph.numNodes()));
	
		// Passo 2 aggiorno il valore 1-d/N + d Somma( numero iniziale pagine/ sizeIndegree) ----> viene effettuato 
		//in base a qunte pagine ha come predecessori
		List<Double> finalRank = new ArrayList<Double>();
		for(int i= 0 ; i< graph.numNodes() ; i++){
			if(pred.get(i).getPredecessor()!= null){
				double value =0 ;
				for(int j=0 ; j< pred.get(i).getPredecessor().size();j++){
					for ( int k=0 ; k<pred.size();k++){
						if(pred.get(i).getPredecessor().get(j) == pred.get(k).getNode()){
							value = value + d*(initialRank.get(k)/succ.get(k).getsuccessor().length);
						}
					}
					
				}
				finalRank.add(initialRank.get(i)+value);
			}
			else{
				finalRank.add(initialRank.get(i));
			}
		}
		
	//	System.out.println("finalRank"+finalRank);
		return finalRank;
	}
	
	
	public double getPageRankIntex(List<Double>pageRank){
		double max = 0.0;
		for(int i= 0 ;i< pageRank.size();i++){
			if(max<pageRank.get(i))max=pageRank.get(i);
		}
		return max;
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
