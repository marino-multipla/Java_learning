package streams;

import java.io.File;
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

}//end of class Methods_TXT
