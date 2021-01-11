package generics;

import java.util.ArrayList;

/**
 * DATE:06/01/21
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to implement
 * a class that admits generic type;
 */
public  class Analysis_Box_Generics<T>{

    /*
     * DECLARE attributes of the class
     */

    private ArrayList<T> list_of_values;
    private String analysis_name;

    /*
     * DECLARE methods of the class
     */

    public Analysis_Box_Generics() {

    }

    public Analysis_Box_Generics(ArrayList<T> list_of_values, String analysis_name) {
        this.list_of_values = list_of_values;
        this.analysis_name = analysis_name;
    }

    public ArrayList<T> getList_of_values() {
        return list_of_values;
    }

    public void setList_of_values(ArrayList<T> list_of_values) {
        this.list_of_values = list_of_values;
    }

    public String getAnalysis_name() {
        return analysis_name;
    }

    public void setAnalysis_name(String analysis_name) {
        this.analysis_name = analysis_name;
    }

}//end of class Analysis_Box_Generics
