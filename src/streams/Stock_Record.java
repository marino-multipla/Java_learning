package streams;

/**
 * DATE:11/01/21
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to represent a Stock Record that is the
 * value of the stock in a given timestamp;
 */
public class Stock_Record {

    private String date;
    private double price;
    private int quantity;

    public Stock_Record() {

    }

    public Stock_Record(String date, double price, int quantity) {
        this.date = date;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return this.date+";"+this.price+";"+this.quantity;
    }

    public static Stock_Record parse(String string_stock_record)throws Exception{

        try{

            String string_temp = null;
            double double_temp = 0;
            int int_temp = 0;
            Stock_Record stock_record = new Stock_Record();

            //extract data
            string_temp = string_stock_record.substring(0, string_stock_record.indexOf(";"));
            stock_record.setDate(string_temp);
            string_stock_record = string_stock_record.substring(string_stock_record.indexOf(";")+1);

            //extract price
            string_temp = string_stock_record.substring(0, string_stock_record.indexOf(";"));
            double_temp = Double.valueOf(string_temp);
            stock_record.setPrice(double_temp);
            string_stock_record = string_stock_record.substring(string_stock_record.indexOf(";")+1);

            //extract quantity
            string_temp = string_stock_record.substring(0, string_stock_record.indexOf(";"));
            int_temp = Integer.valueOf(string_temp);
            stock_record.setQuantity(int_temp);

            return stock_record;

        }//end of try block
        catch(Exception e){
            System.err.println("Error during parse of "+string_stock_record);
            e.printStackTrace();
            throw e;
        }//end of catch block

    }//end of method parse(...)

}//end of class Stock_Record
