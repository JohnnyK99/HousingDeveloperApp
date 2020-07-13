package Others;

import java.util.HashMap;
import java.util.Iterator;

public class Time extends Thread {
    private int day;
    private HashMap<String, Person> people;

    public Time(HashMap<String, Person> people){
        day = 0;
        this.people = people;
    }

    public void run(){
        while(!isInterrupted()){
            try {
                if(day % 2 == 0)
                    checkLeases();
                sleep(5000);
                day++;
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void checkLeases(){
        for (String person : people.keySet()) {

            Iterator<Lease> iter = people.get(person).getLeases().iterator();

            while (iter.hasNext()) {
                Lease lease = iter.next();

                if (lease.getEnd() + 30 <= day) {
                    boolean delete = lease.clearing(day);
                    if(delete) iter.remove();
                }

                else if (lease.getEnd() == day || day - lease.getEnd() == 1)
                    lease.leaseExpiration(day);
            }
        }
    }

    public int getDay(){
        return day;
    }
}

