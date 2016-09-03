package com.crakama.refugee;

/**
 * Created by cate.rakama@gmail.com on 9/3/2016.
 */
public class DadaabCamp {


    private String name;
    private String description;

    public static final DadaabCamp[] camps = {

            new DadaabCamp("IFO ONE","Android is a mobile operating system developed by Google."),
            new DadaabCamp("IFO TWO","Python is a computer programming language "),
            new DadaabCamp("IFO THREE", "Java is a high-level programming language ."),
            new DadaabCamp("KAMBIOS", "Used to creatively design web pages using HTML and CSS. "),
            new DadaabCamp("DAGAHALEY","is a web application framework written in Ruby under the MIT License")
    };

    //Each course has a name and description
    public DadaabCamp(String name, String description) {

        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }
    //The string representation of a Course is its name
    public  String toString(){
        return  this.name;
    }
}
