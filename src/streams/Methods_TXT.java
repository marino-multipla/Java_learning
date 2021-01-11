package streams;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * DATE:11/01/21
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class contains methods that are used to
 * operate on TXT files;
 */
public class Methods_TXT {

    /**
     * This method is used to load ArrayList<Stock_Record> from TXT file;
     * The .txt extension is added automatically
     * @param path
     * @param file_name
     * @return
     * @throws Exception
     */
    public static ArrayList<Stock_Record> load_AL_Stock_Record(String path, String file_name)throws Exception{

        try {
            ArrayList<Stock_Record> list_of_stock_record = new ArrayList<Stock_Record>();
            String data = null;
            File myObj = new File(path+file_name+".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                list_of_stock_record.add(Stock_Record.parse(data));
            }

            myReader.close();
            return list_of_stock_record;

        } catch (Exception e) {
            System.err.println("Error during load_AL_Stock_Record");
            e.printStackTrace();
            throw e;
        }

    }//end of method load_AL_Stock_Record(...)

    /**
     * This method is used to re_write a TXT file by duplicating its lines
     * until 4Million lines;
     * @param path
     * @param file_name
     * @return
     * @throws Exception
     */
    public static void increase_dimension(String path, String file_name)throws Exception{

        try {

            //LOAD original dataset
            ArrayList<String> dataset = new ArrayList<String>();
            String data = null;
            File myObj = new File(path+file_name+".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                dataset.add(data);
            }//end while cycle over myReader
            myReader.close();

            //GET number_of_lines
            int size = dataset.size();

            if(size > 4000000){
                return;
            }

            File file = new File(path+file_name+"_big.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bw = new BufferedWriter(fw);

            while(size < 4000000){

                for(int i=0; i<dataset.size(); i++){
                    bw.write(dataset.get(i));
                    bw.newLine();
                    size++;
                }//end for cycle over dataset

            }//end while cycle over dataset


            bw.close();

        } catch (Exception e) {
            System.err.println("Error during increase_dimension");
            e.printStackTrace();
            throw e;
        }

    }//end of method increase_dimension

}//end of class Methods_TXT
