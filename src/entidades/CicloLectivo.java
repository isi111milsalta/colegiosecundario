/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import conexiones.ConexionBase;
import static conexiones.ConexionBase.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Vicentelo
 */
public class CicloLectivo {
  //ConexionBase conexion = new ConexionBase();
 private int idperiodolectivo; 
 private int año;
 private  Date fechainicio;
 private  Date fechafin;
 private  Date fechainicioreceso;
 private Date fechafinreceso;
 private String estado;
  //variable

public CicloLectivo(int año, Date fechainicio, Date fechafin, Date fechainicioreceso, Date fechafinreceso, String estado) {
        this.año = año;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;//constructor
        this.fechainicioreceso = fechainicioreceso;
        this.fechafinreceso = fechafinreceso;
        this.estado = estado;
}

    public CicloLectivo() {
        
    }

public void RegistrarAño(){
    Connection base= ConexionBase.conexion;
    
     try{
PreparedStatement valores = base.prepareStatement("INSERT INTO periodolectivo (año, fechainicio, fechafin, fechainicioreceso,fechafinreceso, estado ) VALUES(?,?,?,?,?,?)");
  
    
    valores.setInt(1, this.año);
    valores.setDate(2, this.fechainicio);
    valores.setDate(3, this.fechafin);
   valores.setDate(4, this.fechainicioreceso);
    valores.setDate(5, this.fechafinreceso);
    valores.setString(6,this.estado);
    
   
    
    valores.executeUpdate();
    JOptionPane.showMessageDialog(null, " se registro correctamente");
} catch(NumberFormatException | SQLException e){
        JOptionPane.showMessageDialog(null, " no se registro " + e);
}
      //conexion.desconectar();
}
public void modificar(String idperiodolectivo){
    
Connection base= ConexionBase.conexion;     
      try {
                
PreparedStatement valores = base.prepareStatement("UPDATE periodolectivo SET año=?, fechainicio=?,  fechafin=? ,  fechainicioreceso=? ,  fechafinreceso=?,  estado=? WHERE idperiodolectivo="+idperiodolectivo);
    valores.setInt(1, this.año);
    valores.setDate(2, this.fechainicio);
    valores.setDate(3, this.fechafin);
   valores.setDate(4, this.fechainicioreceso);
    valores.setDate(5, this.fechafinreceso);
    valores.setString(6,this.estado);
    
    valores.executeUpdate();
    JOptionPane.showMessageDialog(null, "se modifico correctamente");
} catch(NumberFormatException | SQLException e){
        JOptionPane.showMessageDialog(null, e);
}
      //conexion.desconectar();

}
public TableModel periodo(String anio) {

DefaultTableModel Modelo=new DefaultTableModel();
Modelo.addColumn("Id Periodo");
Modelo.addColumn("Año");
Modelo.addColumn("Fecha Inicio");
Modelo.addColumn("Fecha Fin");
Modelo.addColumn("Fecha Inicio Receso");
Modelo.addColumn("Fecha Fin Receso");
Modelo.addColumn("Estado");
 
String[] fila=new String[7];
// ConexionRemota conexion = new ConexionRemota();
// Connection base=conexion.getConnection();
Connection base= ConexionBase.conexion;
try {
PreparedStatement valores=base.prepareStatement("CALL buscarperiodo ('"+anio+"')");
ResultSet Resultado=valores.executeQuery();
while (Resultado.next()){
fila[0]=Resultado.getString(1);        
fila[1]=Resultado.getString(2);   
fila[2]=Resultado.getString(3); 
fila[3]=Resultado.getString(4); 
fila[4]=Resultado.getString(5); 
fila[5]=Resultado.getString(6); 
fila[6]=Resultado.getString(7); 

Modelo.addRow(fila);}       
return Modelo;
} catch (SQLException ex) {
JOptionPane.showMessageDialog(null,  ex);
return null;
}
}


}

