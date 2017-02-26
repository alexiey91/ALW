package it.uniroma2.algoritmi.web.index;

import it.unimi.dsi.webgraph.ImmutableGraph;
import it.uniroma2.algoritmi.web.utils.Utils;
import it.uniroma2.algoritmi.web.utils.successorObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class dispersionCentrality {

	public dispersionCentrality() {
		// TODO Auto-generated constructor stub
	}

	
	public List<Integer> dispersionCentrality(ImmutableGraph graph){
		
		List<Integer> disp = new ArrayList<Integer>();
		List<successorObject> succesorList = new ArrayList<successorObject>();
		Utils util = new Utils();
		succesorList = util.listOfSuccessorSingle(graph); 

		for(int i=0 ; i<succesorList.size(); i++ ){
			for(int j=0 ; j< succesorList.get(i).getsuccessor().length;j++){
				
					List<Integer> temp = new ArrayList<Integer>();
					temp= intersection(succesorList.get(i).getsuccessor(), succesorList.get(i).getNode(), succesorList.get(j).getsuccessor(), succesorList.get(j).getNode());
				System.out.println("i="+i+" J="+j+" temp="+temp+" successorList"+succesorList.size());
					disp.add(dis(temp, succesorList.get(i).getNode(),succesorList.get(j).getNode(),succesorList));
			//	System.out.println("DIS: " +dis(temp, succesorList.get(i).getNode(),succesorList.get(j).getNode(),succesorList));
				//	count++;
			
			}
			
		}
		
		//System.out.println("count"+ count);
		
	return disp;}
	
	/**
	 * Funzione che calcola l'intersezione tra i nodi in comune tra due nodi in ingresso
	 * */
	public List<Integer> intersection(int []start, int nodeStart , int []next, int nodeNext){
		List<Integer> matching = new ArrayList<Integer>();
		
	for(int i=0 ; i< start.length; i++){
		int temp = start[i];
		//System.out.println("start["+i+"]= "+temp+" start.size="+start.length);
		boolean contains = IntStream.of(next).anyMatch(x -> x == temp );
		if(contains== true && temp != nodeStart && temp != nodeNext) matching.add(temp);
		
	}
		
		
	return matching;}

	public int dis(List<Integer> intersection, int NodeStart, int NodeEnd, List<successorObject> complete){
		int dis=0;
		
		for(int i=0 ; i< intersection.size();i++){
			for(int j=0 ; j< intersection.size();j++){
				if(i!=j){
					int nodeS = complete.get(intersection.get(i)).getNode();
					int nodeF = complete.get(intersection.get(j)).getNode();

					boolean matchS =  IntStream.of(complete.get(intersection.get(j)).getsuccessor()).anyMatch(x -> x == nodeS );
					boolean matchF =  IntStream.of(complete.get(intersection.get(i)).getsuccessor()).anyMatch(x -> x == nodeF );

					if(matchS == false && matchF == false){
					List<Integer > test = intersection(complete.get(intersection.get(i)).getsuccessor(), NodeStart, complete.get(intersection.get(j)).getsuccessor(),NodeEnd);
					if(!test.isEmpty()){ 
						dis= dis;}
					else{ 
						dis= dis +1;
					} 
					
					}else{
						dis= dis;
						}
					
				}
			}
		}
		
		
	return dis;}


}


