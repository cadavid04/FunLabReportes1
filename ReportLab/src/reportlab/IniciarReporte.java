/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportlab;

/**
 *
 * @author itmanager
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
public class IniciarReporte {
Connection conn=null;

public IniciarReporte(){
    try{
        Class.forName("com.mysql.jdbc.Driver"); //se carga el driver
        conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Reports","root","root");
        JOptionPane.showMessageDialog(null,"Conexión establecida");
        } catch (Exception ex){
        ex.printStackTrace();
        }
}
public void ejecutarReporte(int matricula){
    try{
        String archivo = "ReportOne.jasper";
        System.out.println("Cargando desde: " + archivo);
        if(archivo == null){
            System.out.println("No se encuentra el archivo.");
            System.exit(2);
        }
    JasperReport masterReport= null;
        try { masterReport=(JasperReport)JRLoader.loadObjectFromFile(archivo);}
            catch (JRException e) {
System.out.println("Error cargando el reporte maestro: " + e.getMessage());
System.exit(3);
}
//este es el parámetro, se pueden agregar más parámetros
//basta con poner mas parametro.put
Map parametro= new HashMap();
parametro.put("matricula",matricula);//Reporte diseñado y compilado con iReport
JasperPrint jasperPrint= JasperFillManager.fillReport(masterReport,parametro,conn);
//Se lanza el Viewer de Jasper, no termina aplicación al salir
JasperViewer jviewer= new JasperViewer(jasperPrint,false);
jviewer.setTitle("Diego -Reporte");
jviewer.setVisible(true);}
    catch (Exception j){System.out.println("Mensaje de Error: "+ j.getMessage());}}
public void cerrar(){
    try {conn.close();}
        catch(SQLException ex) {ex.printStackTrace();}}
}