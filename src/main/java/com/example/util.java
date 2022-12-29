package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class util {

    public static void print(String s){
        System.out.println(s);
    }

    public static List<String> readInput(String filename){
        List<String> result = new ArrayList<>();
        try{
            // File path is passed as parameter
            File file = new File(filename);
    
            // Note:  Double backquote is to avoid compiler
            // interpret words
            // like \test as \t (ie. as a escape sequence)
    
            // Creating an object of BufferedReader class
            BufferedReader br
                = new BufferedReader(new FileReader(file));
    
            // Declaring a string variable
            String st="";
            // Condition holds true till
            // there is character in a string
            String paragraph = "";
            while ((st = br.readLine()) != null){
                st=st.trim();
                // Print the string
                System.out.println(st);
                if(st.isEmpty()){
                    result.add(paragraph.substring(0,paragraph.length()));
                    paragraph="";
                }else{
                    paragraph+=st+"\n";
                }
            }

            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }
}