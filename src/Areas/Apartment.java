package Areas;

import java.util.ArrayList;
import Others.Person;

public class Apartment extends Room {
    private ArrayList<Person> tenants;


    public Apartment(double size){
        super(size);
        tenants = new ArrayList<>();
    }

    public void addTenant(Person person){
        tenants.add(person);
    }

    public void removeTenant(Person person){
        tenants.remove(person);
    }

    public void clear(){
        tenants.forEach((l) -> l.getRegistrations().remove(this));
        tenants.clear();
        setRenter(null);
    }

    public void showInfo(){
        System.out.println(this);
        System.out.println(sortedContentstoString(""));
    }

    public String sortedContentstoString(String tabulation){
        StringBuilder sb = new StringBuilder();
        sb.append(tabulation).append("Renter: \n");
        sb.append(tabulation).append("\t").append(getRenter()).append("\n");
        sb.append(tabulation).append("Tenants: \n");
        if(tenants.isEmpty())
            sb.append(tabulation).append("\tNone\n");
        else
            for(Person person : tenants)
            sb.append(tabulation).append("\t").append(person).append("\n");
        return sb.toString();

    }

    public void showTenants(){
        int i = 0;
        System.out.println("Tenants: ");
        if(tenants.isEmpty())
            System.out.println("none\n");
        else
            for(Person tenant : tenants)
                System.out.print(i++ + " " + tenant + "\n");
    }

    public ArrayList<Person> getTenants(){
        return tenants;
    }


    public String toString(){
        return "Housing development " + getHousingDev() + ", building " + getBuilding() + ", apartment " + super.toString();
    }
}
