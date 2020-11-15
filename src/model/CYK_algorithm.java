package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.print.attribute.standard.Sides;

public class CYK_algorithm{

    private String w;
    private HashSet<String>[][] matrix;

    public boolean cyk(Grammar grammar, String w){

        boolean generateString = false;
        matrix = new HashSet[w.length()+1][w.length()+1];
        
        initialize(grammar, w);
        fillMatrix(grammar);

        if (matrix[1][matrix.length-1].contains(grammar.getInitialVariable())) {
            generateString = true;
            System.out.println("La genera mi socio");
        }else
            System.out.println("no la genera");

        return generateString;

    }

    public HashSet<String>[][] getHS(){

        return matrix;

    }

    public String getCad(){
        return w;
    }

    private void fillMatrix(Grammar g){
        
        for (int j = 2; j < matrix.length; j++) {
            System.out.println("j = " + j);
            for (int i = 1; i < (matrix.length-j)+1; i++) {
                System.out.println("i = " + i);
                matrix[i][j] = new HashSet<>();
                for (int k = 1; k <= j-1; k++) {
                    int n = i+k;
                    int m = j-k;
                    System.out.println("X"+i+j + " = " + "X"+i+k+"X"+n+m);
                    HashSet<String> set1 = new HashSet<String>();
                    HashSet<String> set2 = new HashSet<String>();

                    if (matrix[i][k] != null) {
                        set1.addAll(matrix[i][k]);
                    }

                    if (matrix[i+k][j-k] != null) {
                        set2.addAll(matrix[i+k][j-k]);
                    }
                    
                    

                    List<String> list = new ArrayList<>(concatenateSet(set1, set2));
                    
                    HashSet<String> setToAdd = g.isProduceFor(list);
                    //System.out.println(setToAdd.size());
                    if (setToAdd != null) {
                        matrix[i][j].addAll(setToAdd);
                    }
                   

                }
                System.out.println();
            }
            System.out.println("-----------------------------------");
        }

    }

    private void initialize(Grammar g, String w){
        
        String c;
        
        for (int i = 0; i < w.length(); i++) {
            
            c = String.valueOf(w.charAt(i));
            List<String> list = new ArrayList<>();
            list.add(c);

            matrix[i+1][1] = g.isProduceFor(list);

        }

    }

    private HashSet<String> concatenateSet(HashSet<String> s1, HashSet<String> s2){

        HashSet<String> set = new HashSet<>();

        if (!(s1.size() == 0 || s2.size() == 0)) {
            System.out.println("Entre a concatenar");
            for (String string : s1) {
                
                for (String string2 : s2) {
                    
                    set.add(string+string2);

                }
            }
        }else{
            System.out.println("no entre a concatenar");
        }

        return set;

    }

}