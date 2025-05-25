/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import org.mindrot.jbcrypt.BCrypt;
import java.util.Arrays;
/**
 *
 * @author migue
 */
public class PasswordHasher {
    private final int LOG_ROUNDS = 10; //higher the value , more hashed the password but more consumption.
    
    public String hashPassword(char[] plainPassword){
        String salt = BCrypt.gensalt(LOG_ROUNDS); 
        String hashedPassword = BCrypt.hashpw(new String(plainPassword), salt); //gen the hashed password
        Arrays.fill(plainPassword, ' '); //clear the array for security
        
        return hashedPassword;
    }
    
    public boolean checkPassword(char[] plainPassword, String hashedPassword){
        boolean matches = BCrypt.checkpw(new String(plainPassword), hashedPassword);
        Arrays.fill(plainPassword, ' '); //clear the array for security
        return matches;
    }
}
