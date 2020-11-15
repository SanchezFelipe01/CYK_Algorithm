package model;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public class Grammar {

	private String initialVariable;
	private Hashtable<String, List<String>> productions;

	public Grammar(String initialVariable, Hashtable<String, List<String>> productions) {

		this.initialVariable = initialVariable;
		this.productions = productions;

	}

	public String getInitialVariable(){
		return initialVariable;
	}

	public Hashtable<String, List<String>> getProductions(){
		return productions;
	}

	public HashSet<String> isProduceFor(List<String> p){

		HashSet<String> variables = new HashSet<String>();

		for (String c : productions.keySet()){
			
			for (String s : p) {
				
				if (productions.get(c).contains(s)) {
					System.out.println(c + " produce a " + "'" + s + "'");
					variables.add(c);
				}else{
					System.out.println(c + " no produce a " + "'" + s + "'");
				}
			}
			
		}
		System.out.println("size de variables = " + variables.size());

		if (variables.size() == 0) {
			variables = null;
		}

		return variables;

	}
	
}
