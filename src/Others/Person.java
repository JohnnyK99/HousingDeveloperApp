package Others;

import Areas.*;
import Exceptions.*;
import Things.*;

import java.util.ArrayList;
import java.util.Collections;

public class Person {
    private String name;
    private String surname;
    private String PESEL;  //id number given at birth in Poland
    private String address;
    private String birthDate;
    private ArrayList<Lease> leases;
    private ArrayList<Apartment> registrations; // as a tenant of an apartment
    private ArrayList<File> files;

    public Person(String name, String surname, String PESEL, String address, String birthDate){
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.address = address;
        this.birthDate = birthDate;
        leases = new ArrayList<>();
        registrations = new ArrayList<>();
        files = new ArrayList<>();
    }

    public void lease(Room room, int day) throws ProblematicTenantException {
        if(files.stream().filter((file) -> file.getRoom().getHousingDev() == room.getHousingDev()).count() > 3)
            throw new ProblematicTenantException("Person " + this.getNameandSurname() + " has already leased these rooms: \n" + leasesToString());

        else {
            if (leases.size() >= 5)
                System.out.println("\nERROR: Too many currently leased rooms.");

            else if (room.getRenter() != null)
                System.out.println("\nERROR: This room is already leased by " + room.getRenter().getNameandSurname() + ".");

            else if(room instanceof ParkingSpace)
                if(leases.stream().noneMatch((file) -> file.getRoom().getHousingDev() == room.getHousingDev()))
                    System.out.println("\nERROR: You have to lease at least one apartment at this housing development to be able to lease a parking space.");

                else {
                    leases.add(new Lease(this, room, day));
                    System.out.println("\nRoom leased successfully.");
                }

            else {
                leases.add(new Lease(this, room, day));
                System.out.println("\nRoom leased successfully.");
            }
        }
    }

    public void renewLease(Room room){
        if(room.getRenter() == this) {
            for (Lease lease : leases)
                if (lease.getRoom() == room) {
                    lease.setEnd(lease.getEnd() + 30);
                    files.removeIf((file) -> file.getRoom() == room);
                    System.out.println("\nLease renewed successfully");
                }
        }
        else
            System.out.println("\nERROR: You are not currently leasing this room.");
    }

    public void cancelLease(Room room){
        if(room.getRenter() == this) {
            leases.removeIf((n) -> n.getRoom() == room);
            files.removeIf((n) -> n.getRoom() == room);
            room.clear();
            System.out.println("\nLease cancelled successfully.");
        }
        else
            System.out.println("\nERROR: You are not currently leasing this room.");
    }

    public void addRegistration(Apartment apartment){
        registrations.add(apartment);
    }

    public void removeRegistration(Apartment apartment){
            registrations.remove(apartment);
    }

    public void registerTenant(Apartment apartment, Person person){
        if(apartment.getRenter() == this) {
            if(!person.getRegistrations().contains(apartment)) {
                apartment.addTenant(person);
                person.addRegistration(apartment);
                System.out.println("\nTenant registered successfully");
            }
            else
                System.out.println("\nERROR: This person is already a tenant of this apartment.");
        }
        else
            System.out.println("\nERROR: You are not currently leasing this room.");
    }

    public void deregisterTenant(Apartment apartment, Person person){
        if(apartment.getRenter() == this) {
            if(person.getRegistrations().contains(apartment)) {
                apartment.removeTenant(person);
                person.removeRegistration(apartment);
                System.out.println("\nTenant deregistered successfully.");
            }
            else
                System.out.println("\nERROR: This person is not a tenant of this apartment.");
        }
        else
            System.out.println("\nERROR: You are not currently leasing this room.");
    }

    public void insertThing(GarageItem thing, ParkingSpace parkingSpace) throws TooManyThingsException{
        if(thing.getSize() > parkingSpace.freeSpace()){
            throw new TooManyThingsException("Remove some old items to insert a new item.");
        }
        else {
            parkingSpace.addThing(thing);
            System.out.println("\n" + thing.getName() + " inserted successfully.");
        }
    }

    public void takeOutThing(GarageItem thing, ParkingSpace parkingSpace){
        parkingSpace.removeThing(thing);
        System.out.println("\n" + thing.getName() + " taken out successfully.");
    }

    public void addFile(Room room, int day){
        files.add(new File(room,day));
    }

    public void removeFile(Room room){
        files.removeIf((file) -> file.getRoom() == room);
    }

    public void showInfo(){
        System.out.println(this);

        System.out.println("Leased rooms: ");
        if (leases.isEmpty())
            System.out.println("none");
        else
            getSortedLeases().forEach(System.out::println);

        System.out.println("\nRegistrations: ");
        if(registrations.isEmpty())
            System.out.println("none");
        else
            registrations.forEach(System.out::println);

        System.out.println("\nFiles: ");
        if(files.isEmpty())
            System.out.println("none");
        else
            files.forEach(System.out::println);
    }

    public ArrayList<Lease> getSortedLeases() {
        Collections.sort(leases);
        return leases;
    }

    public String getPESEL(){
        return PESEL;
    }
    public String getNameandSurname(){
        return name + " " + surname;
    }
    public ArrayList<Lease> getLeases() { return leases; }
    public ArrayList<Apartment> getRegistrations() { return registrations;}
    public ArrayList<File> getFiles(){
        return files;
    }

    public String leasesToString(){
        StringBuilder sb = new StringBuilder();
        leases.forEach(
                (Lease lease) -> sb.append(lease.getRoom()).append("\n")
        );
        return sb.toString();
    }

    public String toString(){
        return name + " " + surname + ", " + PESEL + ", " + address + ", " + birthDate;
    }

}
