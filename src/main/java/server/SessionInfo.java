package server;

import DBofrestaurant.CreationofDB;
import DBofrestaurant.Food;
import DBofrestaurant.Orders;
import DBofrestaurant.Workers;
import MyClasses.Worker;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vita
*
 */
class Tables {

    LinkedList<Integer> tables = new LinkedList<Integer>();
}

public class SessionInfo {

    // public static KitchenPrinter printer;
    public List<Worker> employees;
    public static List<String> kitchenOrder = new ArrayList<String>();
    public static CreationofDB db = new CreationofDB();
    public static Food dbfood = new Food();
    public static Workers dbworkers = new Workers();
    public static Orders dborder = new Orders();

    //public static String password = "1234";  
    public static final int maxTables = 111;
    public static final int maxServers = 50;
    private static SessionInfo reference;
    private int[] tables = new int[maxTables];
    private Tables[] servers = new Tables[maxServers];
    private boolean[] activeServers = new boolean[maxServers]; //true - працівники, що зараз на роботі
    private Map<Integer, Date> clockin;
    public static double[] totalMoney = new double[maxServers];

    private SessionInfo() {
        /* SessionInfo.db.insertWorks(44, "Vita", "Krainik", 1, "server", 10);
        SessionInfo.db.insertWorks(55, "Anzhela", "Semeniuk", 1, "server", 10);
        SessionInfo.db.insertWorks(66, "Tanya", "Voitovych", 1, "server", 10);
        */
        employees = (List<Worker>) SessionInfo.dbworkers.getWorkers();
        clockin = new HashMap<Integer, Date>();
        for (int i = 0; i < maxTables; i++) {
            tables[i] = 0;
        }
        for (int i = 0; i < maxServers; i++) {
            servers[i] = new Tables();
            totalMoney[i] = 0;
        }
    }

    public static SessionInfo getReference() {
        if (reference == null) {
            reference = new SessionInfo();
        }
        return reference;
    }

    synchronized public boolean tableExist(int id) {
        return tables[id] != 0;
    }

    synchronized public void addTable(int id, int serverId) {
        if (!tableExist(id)) {
            tables[id] = serverId;
            servers[serverId].tables.add(id);
        }
    }

    public void deleteTable(int id) {
        servers[serverNumber(id)].tables.remove(Integer.valueOf(id));
        tables[id] = 0;
    }

    public int serverNumber(int id) {
        return tables[id];
    }

    public Iterable getTables(int id) {
        return servers[id].tables;
    }

    public void clockIn(int serverId) {
        activeServers[serverId] = true;
        Calendar cal = Calendar.getInstance();
        clockin.put(serverId, cal.getTime());
    }

    public boolean clockedIn(int serverId) {
        return activeServers[serverId];
    }

    public void clockOut(int serverId) {
        activeServers[serverId] = false;
    }

    public String getClocks(int id) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(this.clockin.get(id));
    }

    public Date getDate(int id) {
        return this.clockin.get(id);
    }
}
