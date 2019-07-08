package sg.edu.rp.c346.carapplication;

import java.io.Serializable;

public class CarDetails implements Serializable {
    private int idcardetails;
    private String name;
    private String release_year;
    private String cost_price;
    private String description;
    private int idcarname;

    public CarDetails(int idcardetails, String name, String release_year, String cost_price, String description, int idcarname) {
        this.idcardetails = idcardetails;
        this.name = name;
        this.release_year = release_year;
        this.cost_price = cost_price;
        this.description = description;

        this.idcarname = idcarname;
    }

    public int getIdcardetails() {
        return idcardetails;
    }

    public String getName() {
        return name;
    }

    public String getRelease_year() {
        return release_year;
    }

    public String getCost_price() {
        return cost_price;
    }

    public String getDescription() {
        return description;
    }



    public int getIdcarname() {
        return idcarname;
    }

    @Override
    public String toString() {
        return name ;
    }
}
