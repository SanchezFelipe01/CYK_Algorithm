package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import model.*;

public class Main extends Application{
    
    public static void main(String[] args){
    
    	launch();
    	/*
        String initial = "S";
        String cad = "aaba";

        ArrayList<String> list_S = new ArrayList<String>();
        //list_S.add("AB");
        list_S.add("BA");
        list_S.add("AC");

        ArrayList<String> list_A = new ArrayList<String>();
        //list_A.add("AA");
        //list_A.add("a");

        list_A.add("CC");
        list_A.add("b");

        ArrayList<String> list_B = new ArrayList<String>();
        //list_B.add("b");

        list_B.add("AB");
        list_B.add("a");

        ArrayList<String> list_C = new ArrayList<String>();

        list_C.add("BA");
        list_C.add("a");

        Hashtable<String, List<String>> t = new Hashtable<String, List<String>>();

        t.put("S", list_S);
        t.put("A", list_A);
        t.put("B", list_B);
        t.put("C", list_C);



        Grammar g = new Grammar(initial, t);
        CYK_algorithm cyk = new CYK_algorithm();
        cyk.setCad(cad);
        cyk.setGrammar(g);
        cyk.cyk();

        HashSet<String>[][] hs = cyk.getHS();
        
        for (int i = 1; i < hs.length; i++) {
            for (int j = 1; j < hs.length; j++) {
                if (hs[i][j] == null) {
                    hs[i][j] = new HashSet<>();
                }
            }
        }

        for (int i = 1; i < hs.length; i++) {
            for (int j = 1; j < hs.length; j++) {
                System.out.print(hs[i][j].size() + "*" + " ");
            }
            System.out.println();
        }*/
   
    }

    @Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("CYK");
		stage.show();
	}


}
