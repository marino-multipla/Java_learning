package streams;

/**
 * DATE:11/01/21
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to represent a Product;
 */
public class Product {

    private String name;
    private int price;

    public Product() {

    }

    public Product(int price, String name) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return this.name+"-"+this.price;
    }

}//end of class Product
