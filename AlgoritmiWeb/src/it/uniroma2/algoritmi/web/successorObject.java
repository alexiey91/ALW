package it.uniroma2.algoritmi.web;

import java.util.ArrayList;
import java.util.List;

public class successorObject {

	private int node;
	private int[] successor ;
	public successorObject() {
		// TODO Auto-generated constructor stub
	}
	
	public successorObject(int node , int[] successor){
	this.node = node;
	this.successor= successor;
	}
	
	public int getNode(){
		return node;
	}
	public int[] getsuccessor(){
	return successor;
	}
	
	public  void setNode(int node){
		this.node = node;
	}
	public void setsuccessor(int[] successor){
		this.successor=successor;
	}


}
