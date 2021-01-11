package generics;

import java.util.ArrayList;

/**
 * DATE:06/01/21
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to implement
 * a class without generics;
 */
public  class Analysis_Box{

    /*
     * DECLARE attributes of the class
     */

    private ArrayList<Integer> list_of_values;
    private String analysis_name;

    /*
     * DECLARE methods of the class
     */

    public Analysis_Box() {

    }

    public Analysis_Box(ArrayList<Integer> list_of_values, String analysis_name) {
        this.list_of_values = list_of_values;
        this.analysis_name = analysis_name;
    }

    public ArrayList<Integer> getList_of_values() {
        return list_of_values;
    }

    public void setList_of_values(ArrayList<Integer> list_of_values) {
        this.list_of_values = list_of_values;
    }

    public String getAnalysis_name() {
        return analysis_name;
    }

    public void setAnalysis_name(String analysis_name) {
        this.analysis_name = analysis_name;
    }

}//end of class Analysis_Box
