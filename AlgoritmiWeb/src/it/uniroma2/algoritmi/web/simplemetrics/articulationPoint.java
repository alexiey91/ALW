package it.uniroma2.algoritmi.web.simplemetrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.KosarajuStrongConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;

public class articulationPoint {

	public articulationPoint() {
		// TODO Auto-generated constructor stub
	}

	
	public static List<String> numberOfWeaklyArticulationPoint(
			DirectedGraph<String, DefaultEdge> grafo, List<Integer> nodi,
			int start_componentiConnesse) {
		
		List<String> setOfArticulationPoint = new ArrayList<String>();
		List<String> setOfEdges = new ArrayList<String>();
		List<String> ListofEdges = new ArrayList<String>();
		String probablyPoint;
		
		nodi = new ArrayList<Integer>(new HashSet<Integer>(nodi));
		
		for( int i=0 ; i<nodi.size();i++){
						
				if(grafo.containsVertex(nodi.get(i).toString())){
				setOfEdges.add(grafo.edgesOf(nodi.get(i).toString()).toString());
			//	System.out.println("setof edges:"+setOfEdges);
			//	System.out.println("set of edges in pos:"+i+"= "+ setOfEdges.get(i).trim().substring(1,setOfEdges.get(i).trim().length()-1 ));
				ListofEdges=parsedListEdges(setOfEdges.get(i).trim().substring(1,setOfEdges.get(i).trim().length()-1 ));
			//	System.out.println("LISTOFEDGES"+ListofEdges);
				probablyPoint = nodi.get(i).toString();
				grafo.removeVertex(nodi.get(i).toString());
				
				
				ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<String, DefaultEdge>(
						grafo);
			//	System.out.println("inspector:"+inspector.connectedSets());
				if (inspector.connectedSets().size() > start_componentiConnesse) {
			//	System.out.println("dentro if ispector at time: "+i);
			//		System.out.println("True wealpoint position:"+i);
					grafo.addVertex(probablyPoint);
					for(int j=0; j< ListofEdges.size();j++){
						if(j%2!=0){
							grafo.addEdge(ListofEdges.get(j - 1).toString(), ListofEdges.get(j)
									.toString());
						}
					}
				//	grafo.addEdge(setOfEdges.get(i), setOfEdges.get(i+1));
					//grafo.addVertex(nodi.get(i).toString());
					/*System.out.println("GRAFO con Nodo RIaggiunto: "
							+ grafo);*/
					
		//			System.out.println("dentro if 2: "							+ inspector.connectedSets().size());
					setOfArticulationPoint.add(probablyPoint);
				}else {//System.out.println("No Articolation point at position:"+i);
				grafo.addVertex(probablyPoint);
				
				for(int j=0; j< ListofEdges.size();j++){
					if(j%2!=0){
					
						grafo.addEdge(ListofEdges.get(j - 1), ListofEdges.get(j)
								.toString());
					}
				}
				
				}
		
				}
				
		}
		
			
		return setOfArticulationPoint;	}
	
	
	public static List<String> numberOfStrongArticulationPoint(
			DirectedGraph<String, DefaultEdge> grafo, List<Integer> nodi,
			int start_componentiConnesse) {
		
		List<String> setOfArticulationPoint = new ArrayList<String>();
		List<String> setOfEdges = new ArrayList<String>();
		List<String> ListofEdges = new ArrayList<String>();
		String probablyPoint;
		
		nodi = new ArrayList<Integer>(new HashSet<Integer>(nodi));
		
		for( int i=0 ; i<nodi.size();i++){
						
				if(grafo.containsVertex(nodi.get(i).toString())){
				setOfEdges.add(grafo.edgesOf(nodi.get(i).toString()).toString());
			//	System.out.println("setof edges:"+setOfEdges);
			//	System.out.println("set of edges in pos:"+i+"= "+ setOfEdges.get(i).trim().substring(1,setOfEdges.get(i).trim().length()-1 ));
				ListofEdges=parsedListEdges(setOfEdges.get(i).trim().substring(1,setOfEdges.get(i).trim().length()-1 ));
				//System.out.println("LISTOFEDGES"+ListofEdges);
				probablyPoint = nodi.get(i).toString();
				grafo.removeVertex(nodi.get(i).toString());
				
				
				KosarajuStrongConnectivityInspector<String,DefaultEdge>finder = new KosarajuStrongConnectivityInspector<String,DefaultEdge>( grafo);

			//	System.out.println("finder:"+finder.stronglyConnectedSets());
				if (finder.stronglyConnectedSets().size() > start_componentiConnesse) {
			//	System.out.println("dentro if finder at time: "+i);
				//	System.out.println("True strongpoint position:"+i);
					grafo.addVertex(probablyPoint);
					for(int j=0; j< ListofEdges.size();j++){
						if(j%2!=0){
							grafo.addEdge(ListofEdges.get(j - 1).toString(), ListofEdges.get(j)
									.toString());
						}
					}
				//	grafo.addEdge(setOfEdges.get(i), setOfEdges.get(i+1));
					//grafo.addVertex(nodi.get(i).toString());
					/*System.out.println("GRAFO con Nodo RIaggiunto: "
							+ grafo);*/
					
				//	System.out.println("dentro if 2 finder: "	+ finder.stronglyConnectedSets().size());
					setOfArticulationPoint.add(probablyPoint);
				}else {//System.out.println("No Strong Articolation point at position:"+i);
				grafo.addVertex(probablyPoint);
				
				for(int j=0; j< ListofEdges.size();j++){
					if(j%2!=0){
					
						grafo.addEdge(ListofEdges.get(j - 1), ListofEdges.get(j)
								.toString());
					}
				}
				
				}
		
				}
				
		}
		
			
		return setOfArticulationPoint;	}
	
public static ArrayList<String> parsedListEdges(String s){
		
		if(s!= ""){
		String replace = s.replace("(","");
		String replace1 = replace.replace(")","");
		String replace2 = replace1.replace(" ","");
		String replace3 = replace2.replace(":", ",");
		
		List<String> arrayList = new ArrayList<String>    (Arrays.asList(replace3.split(",")));
		List<String> favList = new ArrayList<String>();
		for(String fav:arrayList){
		    favList.add(fav.trim());
		}
		
		
		return (ArrayList<String>) favList;
		}
		else return null;
		
	}
}
