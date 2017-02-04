package it.uniroma2.algoritmi.web;

import it.unimi.dsi.webgraph.ImmutableGraph;

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
		succesorList = util.listOfSuccessor(graph); 

		for(int i=0 ; i<succesorList.size(); i++ ){
			for(int j=0 ; j< succesorList.size();j++){
				if(i!=j){
					List<Integer> temp = new ArrayList<Integer>();
					temp= intersection(succesorList.get(i).getsuccessor(), succesorList.get(i).getNode(), succesorList.get(j).getsuccessor(), succesorList.get(j).getNode());
				
					disp.add(dis(temp, succesorList.get(i).getNode(),succesorList.get(j).getNode(),succesorList));
				
				}
			}
			
		}
		
		
		
	return disp;}
	
	/**
	 * Funzione che calcola l'intersezione tra i nodi in comune tra due nodi in ingresso
	 * */
	public List<Integer> intersection(int []start, int nodeStart , int []next, int nodeNext){
		List<Integer> matching = new ArrayList<Integer>();
		
	for(int i=0 ; i< start.length; i++){
		int temp = start[i];
		boolean contains = IntStream.of(next).anyMatch(x -> x == temp );
		if(contains== true && temp != nodeStart && temp != nodeNext) matching.add(temp);
		
	}
		
		
	return matching;}

	public int dis(List<Integer> intersection, int NodeStart, int NodeEnd, List<successorObject> complete){
		int dis=0;
		System.out.println("NodeStart"+NodeStart+" NodeEnd"+NodeEnd);
		System.out.println("Intersection"+intersection);
		for(int i=0 ; i< intersection.size();i++){
			for(int j=0 ; j< intersection.size();j++){
				if(i!=j){
					int nodeS = complete.get(intersection.get(i)).getNode();
					int nodeF = complete.get(intersection.get(j)).getNode();
					System.out.println("nodeS"+nodeS+" nodeF"+nodeF);
					boolean matchS =  IntStream.of(complete.get(intersection.get(j)).getsuccessor()).anyMatch(x -> x == nodeS );
					boolean matchF =  IntStream.of(complete.get(intersection.get(i)).getsuccessor()).anyMatch(x -> x == nodeF );
					System.out.println("matchS"+matchS+" matchF"+matchF);
					if(matchS == false && matchF == false){
						System.out.println("false");
					List<Integer > test = intersection(complete.get(intersection.get(i)).getsuccessor(), NodeStart, complete.get(intersection.get(j)).getsuccessor(),NodeEnd);
					System.out.println("test for i="+i+" j="+j+" :"+test);
					if(!test.isEmpty()){ 
						System.out.println("se test== null"+dis);
						dis= dis;}
					else{ 
						dis= dis +1;
						System.out.println("Se tes== null"+dis);
					} 
					
					}else{
						dis= dis;
						System.out.println("se matchS = true && matchF= true"+dis);
						}
					
				}
			}
		}
		
		
	return dis;}


}


