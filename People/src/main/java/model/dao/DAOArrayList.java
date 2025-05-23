package model.dao;

import model.entity.Person;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import utils.FileManagement;

/**
 * This class implements the IDAO interface and completes the code blocks of 
 * the functions so that they can operate with an ArrayList structure. Thanks 
 * to the overriding of the "equals" method in the Person class, the ArrayList 
 * will not be able to contain objects with the same NIF.
 * @author Francesc Perez 
 * @version 1.1.0
 */
public class DAOArrayList implements IDAO{
    
    ArrayList <Person> people = new ArrayList<>();

    @Override
    public Person read(Person p){
        return people.contains(p) ? people.get(people.indexOf(p)) : null;
    }
    
    @Override
    public void insert(Person p) {
        people.add(p);
    }
    
    @Override
    public void delete(Person p){
        people.remove(p);
    }
    
    @Override
    public void deleteAll(){
        people.clear();
    }
    
    @Override
    public void update(Person p){
        people.set(people.indexOf(p), p);
    }
    
    @Override
    public ArrayList<Person> readAll(){
        return people;
    }
    @Override
    public int count(){
        int cont = 0;
        for(Person person : people) {
            cont ++;
        }
        return cont;
    }
   @Override
    public void exportToCsv() throws Exception {
        //retrieve the file data
        ArrayList<Person> people = this.readAll();
        //insert the people into csv 
        for(Person p:people){
            String csv = p.getNif() + "," + p.getName() + "," + p.getPostalCode() + "," + p.getPhoneNumber() + "," + p.getEmail() + "," +  p.getDateOfBirth() + "," + p.getPhoto();
            FileManagement fm = new FileManagement();
            fm.fileWriter(csv);
        }
    }
}
