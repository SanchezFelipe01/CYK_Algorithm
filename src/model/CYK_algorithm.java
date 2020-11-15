package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CYK_algorithm{

    private String w;
    private Grammar grammar;
    private HashSet<String>[][] matrix;
    
    public CYK_algorithm() {
    	
    	this.grammar = null;
    	this.w = null;
    	
    }

    @SuppressWarnings("unchecked")
	public boolean cyk(){

        boolean generateString = false;
       
        matrix = new HashSet[w.length()+1][w.length()+1];
        
        initialize();
        fillMatrix();

        if (matrix[1][matrix.length-1].contains(grammar.getInitialVariable())) {
            generateString = true;
            
        }
    
        return generateString;

    }

    public HashSet<String>[][] getHS(){

        return matrix;

    }

    public String getCad(){
        return w;
    }
    
    public void setGrammar(Grammar grammar) {
    	this.grammar = grammar;
    }
    
    public void setCad(String w) {
    	this.w = w;
    }

    private void fillMatrix(){
        
        for (int j = 2; j < matrix.length; j++) {
            
            for (int i = 1; i < (matrix.length-j)+1; i++) {
                
                matrix[i][j] = new HashSet<>();
                
                for (int k = 1; k <= j-1; k++) {
                    int n = i+k;
                    int m = j-k;
                    
                    HashSet<String> set1 = new HashSet<String>();
                    HashSet<String> set2 = new HashSet<String>();

                    if (matrix[i][k] != null) {
                        set1.addAll(matrix[i][k]);
                    }

                    if (matrix[i+k][j-k] != null) {
                        set2.addAll(matrix[i+k][j-k]);
                    }
                    
                    

                    List<String> list = new ArrayList<>(concatenateSet(set1, set2));
                    
                    HashSet<String> setToAdd = grammar.isProduceFor(list);
                    
                    if (setToAdd != null) {
                        matrix[i][j].addAll(setToAdd);
                    }  

                }
               
            }
            
        }

    }

    private void initialize(){
        
        String c;
        
        for (int i = 0; i < w.length(); i++) {
            
            c = String.valueOf(w.charAt(i));
            List<String> list = new ArrayList<>();
            list.add(c);

            matrix[i+1][1] = grammar.isProduceFor(list);

        }

    }

    private HashSet<String> concatenateSet(HashSet<String> s1, HashSet<String> s2){

        HashSet<String> set = new HashSet<>();

        if (!(s1.size() == 0 || s2.size() == 0)) {
            
            for (String string : s1) {
                
                for (String string2 : s2) {
                    
                    set.add(string+string2);

                }
            }
        }

        return set;

    }

}