package it.uniroma2.algoritmi.web.utils;

import java.util.ArrayList;
import java.util.List;

public class predecessorObject {

	private int node;
	private List<Integer> predecessor = new ArrayList<Integer>() ;
	
	public predecessorObject() {
		// TODO Auto-generated constructor stub
	}
	public predecessorObject(int node , List<Integer> predecessor){
		this.node = node;
		this.predecessor= predecessor;
		}
		
		public int getNode(){
			return node;
		}
		public List<Integer> getPredecessor(){
		return predecessor;
		}
		
		public  void setNode(int node){
			this.node = node;
		}
		public void setPredecessor(List<Integer> predecessor){
			this.predecessor=predecessor;
		}
	
}
