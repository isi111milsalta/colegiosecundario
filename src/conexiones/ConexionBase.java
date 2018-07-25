/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Vicentelo
 */
public class ConexionBase {
    
    public static Connection conexion=null;
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="programadoresisi";
    private static final String password="isi111mil";
    private static final String url="jdbc:mysql://db4free.net:3306/bdcolegio";

public ConexionBase(){
    try{
        Class.forName(driver);
        conexion=DriverManager.getConnection(url, user, password);
        

        if (conexion!=null){
        System.out.println("conexion establecida");
//JOptionPane.showMessageDialog(null, "conexion establecida");
        }
        
    } catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null, "error de conexion"+e);
        
    }
}

public Connection getConnection (){
       
         return conexion;
}

public void desconectar(){
    conexion=null;
    if(conexion==null){
        System.out.println("conexion terminada");
    }
}
}




    
    

