package model.dao;

import model.entity.Person;
import start.Routes;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import utils.FileManagement;

/**
 * This class implements the IDAO interface and completes the function code
 * blocks so that they can operate with a SQL DDBB. The NIF is used as the
 * primary key.
 *
 * @author Francesc Perez
 * @version 1.1.0
 */
public class DAOSQL implements IDAO {

    private final String SQL_SELECT_ALL = "SELECT * FROM " + Routes.DB.getDbServerDB() + "." + Routes.DB.getDbServerTABLE() + ";";
    private final String SQL_SELECT = "SELECT * FROM " + Routes.DB.getDbServerDB() + "." + Routes.DB.getDbServerTABLE() + " WHERE (nif = ?);";
    private final String SQL_INSERT = "INSERT INTO " + Routes.DB.getDbServerDB() + "." + Routes.DB.getDbServerTABLE() + " (nif, name, postalCode, phoneNumber, email, dateOfBirth, photo) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private final String SQL_UPDATE = "UPDATE " + Routes.DB.getDbServerDB() + "." + Routes.DB.getDbServerTABLE() + " SET name = ?, postalCode = ? , phoneNumber = ?, email = ?, dateOfBirth = ?, photo = ? WHERE (nif = ?);";
    private final String SQL_DELETE = "DELETE FROM " + Routes.DB.getDbServerDB() + "." + Routes.DB.getDbServerTABLE() + " WHERE (nif = ";
    private final String SQL_DELETE_ALL = "TRUNCATE " + Routes.DB.getDbServerDB() + "." + Routes.DB.getDbServerTABLE();

    public Connection connect() throws SQLException {
        Connection conn;
        conn = DriverManager.getConnection(Routes.DB.getDbServerAddress() + Routes.DB.getDbServerComOpt(), Routes.DB.getDbServerUser(), Routes.DB.getDbServerPassword());
        return conn;
    }

    public void disconnect(Connection conn) throws SQLException {
        conn.close();
    }

    @Override
    public Person read(Person p) throws SQLException {
        Person pReturn = null;
        Connection conn;
        PreparedStatement instruction;
        ResultSet rs;
        conn = connect();
        instruction = conn.prepareStatement(SQL_SELECT);
        instruction.setString(1, p.getNif());
        rs = instruction.executeQuery();
        while (rs.next()) {
            String nif = rs.getString("nif");
            String name = rs.getString("name");
            String postalCode = rs.getString("postalCode");
            String phoneNumber = rs.getString("phoneNumber");
            String email = rs.getString("email");
            pReturn = new Person(name, nif, postalCode, phoneNumber, email);
            Date date = rs.getDate("dateOfBirth");
            if (date != null) {
                pReturn.setDateOfBirth(date);
            }
            String photo = rs.getString("photo");
            if (photo != null) {
                pReturn.setPhoto(new ImageIcon(photo));
            }
        }
        rs.close();
        instruction.close();
        disconnect(conn);
        return pReturn;
    }

    @Override
    public ArrayList<Person> readAll() throws SQLException{
        ArrayList<Person> people = new ArrayList<>();
        Connection conn;
        Statement instruction;
        ResultSet rs;
        conn = connect();
        instruction = conn.createStatement();
        rs = instruction.executeQuery(SQL_SELECT_ALL);
        while (rs.next()) {
            String nif = rs.getString("nif");
            String name = rs.getString("name");
            String postalCode = rs.getString("postalCode");
            String phoneNumber = rs.getString("phoneNumber");
            String email = rs.getString("email");
            Date date = rs.getDate("dateOfBirth");
            String photo = rs.getString("photo");
            if (photo != null) {
                people.add(new Person(nif, name,postalCode, phoneNumber, email, date, new ImageIcon(photo)));
            } else {
                people.add(new Person(nif, name,postalCode, phoneNumber, email, date, null));
            }
        }
        rs.close();
        instruction.close();
        disconnect(conn);
        return people;
    }
    @Override
    public void delete(Person p) throws SQLException {
        Connection conn;
        PreparedStatement instruction;
        conn = connect();
        String query = SQL_DELETE + "'" + p.getNif() + "'" + ");";
        instruction = conn.prepareStatement(query);
        instruction.executeUpdate();
        instruction.close();
        disconnect(conn);
        File photoFile = new File(Routes.DB.getFolderPhotos() + File.separator + p.getNif()
                + ".png");
        photoFile.delete();
    }
    @Override
    public void insert(Person p) throws IOException, SQLException {
        Connection conn;
        PreparedStatement instruction;
        conn = connect();
        instruction = conn.prepareStatement(SQL_INSERT);
        instruction.setString(1, p.getNif());
        instruction.setString(2, p.getName());
        instruction.setString(3, p.getPostalCode());
        instruction.setString(4, p.getPhoneNumber());
        instruction.setString(5, p.getEmail());
        if (p.getDateOfBirth() != null) {
            instruction.setDate(6, new java.sql.Date((p.getDateOfBirth()).getTime()));
        } else {
            instruction.setDate(6, null);
        }
        if (p.getPhoto() != null) {
            String sep = File.separator;
            String filePath = Routes.DB.getFolderPhotos() + sep + p.getNif() + ".png";
            File photo = new File(filePath);
            FileOutputStream out;
            BufferedOutputStream outB;
            out = new FileOutputStream(photo);
            outB = new BufferedOutputStream(out);
            BufferedImage bi = new BufferedImage(p.getPhoto().getImage().getWidth(null),
                    p.getPhoto().getImage().getHeight(null),
                    BufferedImage.TYPE_INT_ARGB);
            bi.getGraphics().drawImage(p.getPhoto().getImage(), 0, 0, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);
            byte[] img = baos.toByteArray();
            for (int i = 0; i < img.length; i++) {
                outB.write(img[i]);
            }
            outB.close();
            instruction.setString(7, photo.getPath());
        } else {
            instruction.setString(7, null);
        }
        instruction.executeUpdate();
        instruction.close();
        disconnect(conn);
    }

    @Override
    public void update(Person p) throws FileNotFoundException, SQLException, IOException {
        Connection conn;
        PreparedStatement instruction;
        conn = connect();
        instruction = conn.prepareStatement(SQL_UPDATE);
        instruction.setString(1, p.getName());
        instruction.setString(2, p.getPostalCode());
        instruction.setString(3, p.getPhoneNumber());
        instruction.setString(4, p.getEmail());
        if (p.getDateOfBirth() != null) {
            instruction.setDate(5, new java.sql.Date((p.getDateOfBirth()).getTime()));
        } else {
            instruction.setDate(5, null);
        }
        if (p.getPhoto() != null) {
            String sep = File.separator;
            File imagePerson = new File(Routes.DB.getFolderPhotos() + sep + p.getNif() + ".png");
            FileOutputStream out;
            BufferedOutputStream outB;
            out = new FileOutputStream(imagePerson);
            outB = new BufferedOutputStream(out);
            BufferedImage bi = new BufferedImage(p.getPhoto().getImage().getWidth(null),
                    p.getPhoto().getImage().getHeight(null),
                    BufferedImage.TYPE_INT_ARGB);
            bi.getGraphics().drawImage(p.getPhoto().getImage(), 0, 0, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);
            byte[] img = baos.toByteArray();
            for (int i = 0; i < img.length; i++) {
                outB.write(img[i]);
            }
            outB.close();
            instruction.setString(6, imagePerson.getPath());
        } else {
            instruction.setString(6, null);
            File photoFile = new File(Routes.DB.getFolderPhotos() + File.separator + p.getNif()
                    + ".png");
            photoFile.delete();
        }
        instruction.setString(7, p.getNif());
        instruction.executeUpdate();
        instruction.close();
        disconnect(conn);
    }
    @Override
    public void deleteAll() throws Exception {
        Connection conn;
        PreparedStatement instruction;
        conn = connect();
        instruction = conn.prepareStatement(SQL_DELETE_ALL);
        System.out.println(SQL_DELETE_ALL);
        instruction.executeUpdate();
        instruction.close();
        disconnect(conn);
        File file = new File(Routes.DB.getFolderPhotos() + File.separator);
        for(File f : file.listFiles())
            f.delete();
    }
    
    @Override
    public int count() {
        int total = 0;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = connect();
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) AS total FROM " + Routes.DB.getDbServerDB() + "." + Routes.DB.getDbServerTABLE();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Error en count(): " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) disconnect(conn);
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos en count(): " + e.getMessage());
            }
        }
        return total;
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
