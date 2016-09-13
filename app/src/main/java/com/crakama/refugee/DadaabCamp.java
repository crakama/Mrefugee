package com.crakama.refugee;

/**
 * Created by cate.rakama@gmail.com on 9/3/2016.
 */
public class DadaabCamp {
    private String name;
    private String description;

    public static final DadaabCamp[] camps = {

            new DadaabCamp("IFO ONE","Camp one"),
            new DadaabCamp("IFO TWO","camp two "),
            new DadaabCamp("IFO THREE", "Camp three"),
            new DadaabCamp("KAMBIOS", "Camp four"),
            new DadaabCamp("DAGAHALEY","Camp five")
    };
    //Each course has a name and description
    public DadaabCamp(String name, String description) {

        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){ return description; }
    //The string representation of a Course is its name
    public  String toString(){
        return  this.name;
    }
}
