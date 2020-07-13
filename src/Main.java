import Others.*;
import Areas.*;
import Things.*;
import Exceptions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<HousingDevelopment> housingDevelopments = new ArrayList<>();

        housingDevelopments.add(new HousingDevelopment());

        housingDevelopments.get(0).addBuilding(new Building(5));
        housingDevelopments.get(0).addBuilding(new Building(8));
        
        HashMap<String, Person> people = new HashMap<>();

        Person o1 = new Person("Adam", "Kowalski", "90011147758", "Kolorowa 6", "11.01.1990");
        Person o2 = new Person("Agata", "Nowak", "75062446506", "Piękna 19", "24.06.1975");
        Person o3 = new Person("Paweł", "Pietrzak", "80071446659", "Lipowa 20", "14.07.1980");
        Person o4 = new Person("Jan","Adamski","98011254897","Krakowska 8","12.01.1998");
        Person o5 = new Person("Karol","Wójcik","81021045867","Rynkowa 16a","10.02.1981");
        Person o6 = new Person("Karolina", "Bielik","93041363876", "Majowa 8","13.04.1993");

        people.put(o1.getPESEL(), o1);
        people.put(o2.getPESEL(), o2);
        people.put(o3.getPESEL(), o3);
        people.put(o4.getPESEL(), o4);
        people.put(o5.getPESEL(), o5);
        people.put(o6.getPESEL(), o6);

        //initial assignment of people and items to the rooms
        try {
            o1.lease(housingDevelopments.get(0).getBuilding(0).getRoom(1), 0);
            o1.lease(housingDevelopments.get(0).getBuilding(0).getRoom(6), 0);
            o2.lease(housingDevelopments.get(0).getBuilding(1).getRoom(15), 0);
            o3.lease(housingDevelopments.get(0).getBuilding(0).getRoom(5), 0);
            o3.lease(housingDevelopments.get(0).getBuilding(0).getRoom(10), 0);
            o4.lease(housingDevelopments.get(0).getBuilding(1).getRoom(14), 0);
            o5.lease(housingDevelopments.get(0).getBuilding(0).getRoom(2), 0);
            o6.lease(housingDevelopments.get(0).getBuilding(1).getRoom(11), 0);
            o6.lease(housingDevelopments.get(0).getBuilding(1).getRoom(23), 0);
            o6.lease(housingDevelopments.get(0).getBuilding(1).getRoom(25), 0);

            o1.registerTenant((Apartment)housingDevelopments.get(0).getBuilding(0).getRoom(1), o2);
            o1.registerTenant((Apartment)housingDevelopments.get(0).getBuilding(0).getRoom(1), o3);
            o2.registerTenant((Apartment)housingDevelopments.get(0).getBuilding(1).getRoom(15), o1);
            o3.registerTenant((Apartment)housingDevelopments.get(0).getBuilding(0).getRoom(5), o6);
            o4.registerTenant((Apartment)housingDevelopments.get(0).getBuilding(1).getRoom(14), o5);
            o6.registerTenant((Apartment)housingDevelopments.get(0).getBuilding(1).getRoom(11), o4);

            o1.insertThing(new Motorcycle("Kawasaki", 2.5,150,3.5),(ParkingSpace)housingDevelopments.get(0).getBuilding(0).getRoom(6));
            o1.insertThing(new Thing("Grill", 0.7),(ParkingSpace)housingDevelopments.get(0).getBuilding(0).getRoom(6));
            o3.insertThing(new OffRoadCar("Jeep",4,180,true), (ParkingSpace)housingDevelopments.get(0).getBuilding(0).getRoom(10));
            o3.insertThing(new Thing("Cupboard",0.4), (ParkingSpace)housingDevelopments.get(0).getBuilding(0).getRoom(10));
            o3.insertThing(new Thing("Scooter",0.4), (ParkingSpace)housingDevelopments.get(0).getBuilding(0).getRoom(10));
            o6.insertThing(new Thing("Grill",0.8), (ParkingSpace)housingDevelopments.get(0).getBuilding(1).getRoom(23));
            o6.insertThing(new Thing("Winter tires",1.3), (ParkingSpace)housingDevelopments.get(0).getBuilding(1).getRoom(23));
            o3.insertThing(new CityCar("Mazda",4.8,120,7.6), (ParkingSpace)housingDevelopments.get(0).getBuilding(1).getRoom(25));


        } catch (ProblematicTenantException | TooManyThingsException e){
            e.printStackTrace();
        }

        Scanner in = new Scanner(System.in);

        Time time = new Time(people);
        time.start();

        String a;
        Person currUser = null;

        do{
            if (currUser == null) {
                currUser = findPerson(people);
            }
            System.out.println("Choose: " +
                    "\nquit to close the program" +
                    "\n1 to choose a person" +
                    "\n2 to check today's date" +
                    "\n3 to show your personal information" +
                    "\n4 to show free rooms" +
                    "\n5 to lease a room" +
                    "\n6 to renew a lease" +
                    "\n7 to cancel a lease" +
                    "\n8 to show a room with the contents" +
                    "\n9 to register a tenant to an apartment" +
                    "\n10 to deregister a tenant from an apartment" +
                    "\n11 to put an item or a vehicle into a parking space" +
                    "\n12 to get an item or a vehicle out of a parking space" +
                    "\n13 to save current state to a file");

            a = in.next();

            switch (a) {
                case "quit":
                    break;

                case "1": {
                    currUser = findPerson(people);
                    break;
                }
                case "2": {
                    System.out.println("\nDay " + time.getDay());
                    break;
                }

                case "3": {
                    currUser.showInfo();
                    break;
                }

                case "4": {
                    housingDevelopments.forEach(HousingDevelopment::showFreeRooms);
                    break;
                }

                case "5": {
                    Room currRoom = findRoom(housingDevelopments);
                    if(currRoom != null) {
                        try {
                            currUser.lease(currRoom, time.getDay());
                        } catch (ProblematicTenantException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }

                case "6": {
                    System.out.println("\nYour rooms: ");
                    currUser.getSortedLeases().forEach((n) -> System.out.println(n.getRoom()));

                    Room currRoom = findRoom(housingDevelopments);
                    if(currRoom != null)
                        currUser.renewLease(currRoom);
                    break;
                }

                case "7": {
                    System.out.println("\nYour rooms: ");
                    currUser.getSortedLeases().forEach((n) -> System.out.println(n.getRoom()));

                    Room currRoom = findRoom(housingDevelopments);
                    if(currRoom != null)
                        currUser.cancelLease(currRoom);
                    break;
                }

                case "8": {
                    System.out.println("\nYour rooms: ");
                    currUser.getSortedLeases().forEach((n) -> System.out.println(n.getRoom()));

                    Room currRoom = findRoom(housingDevelopments);
                    if(currRoom != null) {
                        if (currRoom.getRenter() == currUser)
                            currRoom.showInfo();
                        else
                            System.out.println("\nERROR: You are not currently leasing this room.");
                    }
                   break;
                }

                case "9": {
                    System.out.println("\nYour rooms:");
                    currUser.getSortedLeases().forEach((n) -> System.out.println(n.getRoom()));

                    Room currRoom = findRoom(housingDevelopments);
                    if(currRoom != null) {
                        if (!(currRoom instanceof Apartment))
                            System.out.println("\nERROR: this room is not an apartment.");
                        else if(currRoom.getRenter() != currUser)
                            System.out.println("\nERROR: You are not currently leasing this room.");
                        else {
                            System.out.println("Enter the registered person's data: ");
                            System.out.print("\tPESEL: ");
                            String pesel = in.next();
                            if (people.containsKey(pesel))
                                currUser.registerTenant((Apartment) currRoom, people.get(pesel));
                            else {
                                Person newPerson = createPerson(pesel);
                                currUser.registerTenant((Apartment) currRoom, newPerson);
                                people.put(pesel, newPerson);
                            }
                        }
                    }
                    break;
                }

                case "10": {
                    System.out.println("\nYour rooms:");
                    currUser.getSortedLeases().forEach((n) -> System.out.println(n.getRoom()));

                    Room currRoom = findRoom(housingDevelopments);
                    if(currRoom != null) {
                        if (!(currRoom instanceof Apartment))
                            System.out.println("\nERROR: this room is not an apartment.");
                        else if(currRoom.getRenter() != currUser)
                            System.out.println("\nERROR: You are not currently leasing this room.");
                        else if (((Apartment)currRoom).getTenants().isEmpty())
                            System.out.println("\nThere are no people registered in this apartment.");
                        else {
                            ((Apartment) currRoom).showTenants();
                            System.out.print("Enter the person's PESEL: ");
                            String pesel = in.next();
                            if (people.containsKey(pesel))
                                currUser.deregisterTenant((Apartment) currRoom, people.get(pesel));
                            else
                                System.out.println("\nERROR: the person could not be found.");
                        }
                    }
                    break;
                }

                case "11": {
                    System.out.println("\nYour rooms:");
                    currUser.getSortedLeases().forEach((n) -> System.out.println(n.getRoom()));

                    Room currRoom = findRoom(housingDevelopments);
                    if(currRoom != null) {
                        if (!(currRoom instanceof ParkingSpace))
                            System.out.println("\nERROR: this room is not a parking space.");
                        else if (currRoom.getRenter() != currUser)
                            System.out.println("\nERROR: You are not currently leasing this room.");
                        else {
                            System.out.println("\nFree space: " + ((ParkingSpace) currRoom).freeSpace() + "m2");
                            createItem(currUser,currRoom);
                        }
                    }
                    break;
                }

                case "12": {
                    System.out.println("\nYour rooms: ");
                    currUser.getSortedLeases().forEach((n) -> System.out.println(n.getRoom()));

                    Room currRoom = findRoom(housingDevelopments);
                    if(currRoom != null) {
                        if (currRoom instanceof Apartment)
                            System.out.println("\nERROR: this room is not a parking space.");
                        else if (currRoom.getRenter() != currUser){
                            System.out.println("\nERROR: You are not currently leasing this room.");
                        }
                        else if (((ParkingSpace)currRoom).getContents().isEmpty())
                            System.out.println("\nThis parking space is empty.");
                        else{
                            System.out.println("Enter the number of item You want to get out.");
                            ((ParkingSpace) currRoom).showContents();
                            int currItemID = in.nextInt();
                            try {
                                currUser.takeOutThing(((ParkingSpace) currRoom).getContents().get(currItemID), (ParkingSpace) currRoom);
                            } catch (IndexOutOfBoundsException e){
                                System.out.println("\nERROR: the item could not be found.");
                            }
                        }
                    }
                    break;
                }

                case "13": {
                    housingDevState(people, time.getDay());
                    break;
                }

                default: {
                    System.out.println("\nERROR: unknown operation");
                }
            }

            if(!a.equals("quit")) {
                System.out.println("\nChoose:" +
                        "\nany key to go back to main menu" +
                        "\nquit to close the program.");
                a = in.next();
            }

        } while (!a.equals("quit"));

        time.interrupt();
    }

    public static Person findPerson(HashMap<String, Person> people){
        Scanner in = new Scanner(System.in);
        String pesel;
        Person user = null;

        do{
            System.out.print("\nEnter your PESEL: ");
            pesel = in.next();
            if(!pesel.equals("quit")){
                if(!people.containsKey(pesel))
                    System.out.println("\nERROR: the person could not be found.");
                else{
                    user = people.get(pesel);

                }
            }
        } while(!people.containsKey(pesel));

        System.out.println("\nChosen user: " + user.getNameandSurname());
        return user;
    }

    public static Room findRoom(ArrayList<HousingDevelopment> housingDevelopments){
        Scanner in = new Scanner(System.in);
        System.out.print("\nEnter the housing development number: ");
        int hd = in.nextInt();
        System.out.print("Enter the building number: ");
        int b = in.nextInt();
        System.out.print("Enter the room number: ");
        int r = in.nextInt();
        try {
            if(housingDevelopments.get(hd).getBuilding(b).getRoom(r) != null)
                return housingDevelopments.get(hd).getBuilding(b).getRoom(r);
        } catch (IndexOutOfBoundsException ex){}

        System.out.println("\nERROR: the room could not be found.");
        return null;
    }

    public static Person createPerson(String pesel){
        Scanner in = new Scanner(System.in);

        System.out.print("\tName: ");
        String name = in.next();

        System.out.print("\tSurname: ");
        String surname = in.next();

        in.nextLine();
        System.out.print("\tAddress: ");
        String address = in.nextLine();


        System.out.print("\tDate of birth (dd.mm.yyyy): ");
        String date = in.next();

        return new Person(name, surname, pesel, address, date);
    }

    public static void createItem(Person currUser, Room currRoom){
        Scanner in = new Scanner(System.in);

        System.out.println("Choose the item type: " +
                "\n1  thing" +
                "\n2  off-road car" +
                "\n3  city car" +
                "\n4  boat" +
                "\n5  motorcycle" +
                "\n6  amphibian");
        int currType = in.nextInt();
        try{
            System.out.print("Name: ");
            String currName = in.next();
            System.out.print("Size: ");
            double currSize = in.nextDouble();
            int currPower = 0;
            if(currType != 1) {
                System.out.print("Power: ");
                currPower = in.nextInt();
            }
            switch (currType) {
                case 1: {
                    currUser.insertThing(new Thing(currName, currSize), (ParkingSpace) currRoom);
                    break;
                }
                case 2: {
                    System.out.println("Does it have a winch?" +
                            "\n1  Yes" +
                            "\n2  No");
                    int tmp = in.nextInt();
                    boolean winch = tmp == 1;
                    currUser.insertThing(new OffRoadCar(currName, currSize,currPower, winch), (ParkingSpace) currRoom);
                    break;
                }
                case 3: {
                    System.out.print("Fuel consumption: (L/100 km)");
                    double fuelCons = in.nextDouble();
                    currUser.insertThing(new CityCar(currName, currSize,currPower,fuelCons), (ParkingSpace) currRoom);
                    break;
                }
                case 4: {
                    System.out.print("Displacement: (kg)");
                    int displacement = in.nextInt();
                    currUser.insertThing(new Boat(currName, currSize,currPower,displacement), (ParkingSpace) currRoom);
                    break;
                }
                case 5: {
                    System.out.print("Acceleration to 100 km/h: ");
                    double acceleration = in.nextDouble();
                    currUser.insertThing(new Motorcycle(currName, currSize,currPower,acceleration), (ParkingSpace) currRoom);
                    break;
                }
                case 6: {
                    System.out.print("Seat count: ");
                    int seatCount = in.nextInt();
                    currUser.insertThing(new Amphibian(currName, currSize,currPower, seatCount), (ParkingSpace) currRoom);
                    break;
                }
                default:
                    System.out.print("\nERROR: invalid type.");
            }
        } catch (InputMismatchException ex) {
            System.out.println("\nERROR: invalid data format.");
        } catch (TooManyThingsException e){
            e.printStackTrace();
        }
    }

    public static void housingDevState(HashMap<String, Person> people, int day){
        StringBuilder sb = new StringBuilder();
        for(String pesel : people.keySet()) {
            sb.append(people.get(pesel)).append("\n");
            sb.append("\tCurrently leased rooms: ").append("\n");

            for(Lease n : people.get(pesel).getSortedLeases()){
                sb.append("\t\t").append(n.getRoom()).append("\n");
                sb.append(n.getRoom().sortedContentstoString("\t\t\t")).append("\n");
            }

            sb.append("\tFiles: ").append("\n");
            if(people.get(pesel).getFiles().isEmpty())
                sb.append("\t\tnone");
            else
                people.get(pesel).getFiles().forEach((p)->sb.append("\t\t").append(p).append("\n"));

            sb.append("\n\n");
        }

        try {
            File stan = new File("state_day" + day + ".txt");
            if(!stan.createNewFile())
                System.out.println("\nERROR: could not create a file");
            else
                System.out.println("\nSaved successfully.");
            FileWriter myWriter = new FileWriter(stan);
            myWriter.write(sb.toString());
            myWriter.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
