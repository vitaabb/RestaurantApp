/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manager.thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import server.Server;

/**
 *
 * @author Vita
 */
public class ManagerClientThread extends Thread{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    public ManagerClientThread(InetAddress addr) throws java.lang.NullPointerException{
        try {
            socket = new Socket(addr, Server.PORT);
        } catch (IOException e) {
            System.err.println("IOException |?|?|?|?|?|??|");
        }
        try {
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())), true);
            start();
        } catch (IOException e) {
                System.err.println("IO EXCEPTION");
        }
    }
    
    public boolean addEmployee(int id, String name, String surname, double salary) throws IOException{
        //still need database
        out.println("{\"method\":"+"\"addEmployee\""+",\"params\":[\""+id+"\",\""+name+"\",\""+surname+"\",\""+salary+"\"]}");
        String str = in.readLine();
        return str.equals("true");
    }
    
    public boolean addCategory(String name) throws IOException{
        //still need database
        out.println("{\"method\":"+"\"addCategory\""+",\"params\":["+name+"]}");
        String str = in.readLine();
        return str.equals("true");
    }
    
    public boolean addDish(String category, String name, String description, double price) throws IOException{
        //still need database
        out.println("{\"method\":"+"\"addDish\""+",\"params\":[\""+category+"\",\""+name+"\",\""+description+"\",\""+price+"\"]}");
        String str = in.readLine();
        return str.equals("true");
    }
    
    public void deleteCategory(String name){
        out.println("{\"method\":"+"\"deleteCategory\""+",\"params\":[\""+name+"\"]}");
    }
    public void deleteDish(String name){
        out.println("{\"method\":"+"\"deleteDish\""+",\"params\":[\""+name+"\"]}");
    }
    
    public void deleteEmployee(int id){
        out.println("{\"method\":"+"\"deleteEmploye\""+",\"params\":[\""+id+"\"]}");
    }
    
    public void changePass(char[] pass){
        String s = "";
        for(int i = 0; i<pass.length; i++){
            s+=pass[i];
        }
        out.println("{\"method\":"+"\"changePass\""+",\"params\":["+s+"]}");
    }
    public boolean checkPass(char[] pass) throws IOException{
        String s = "";
        for(int i = 0; i<pass.length; i++){
            s+=pass[i];
        }
        out.println("{\"method\":"+"\"checkPass\""+",\"params\":[\""+s+"\"]}");
        return in.readLine().equals("true");
    }
    
    
    
    public void closeThread(){
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Socket not closed");
        }
    }
}
