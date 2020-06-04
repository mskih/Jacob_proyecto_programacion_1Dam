/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expo;

import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author M.J.H
 */
public class Expo {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {//un "exception" para conexion de mysql
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        String usuario = "";
        String contra = "";
        System.out.println("Bienvenido a la exposicion de fotografias del salvaje");
        System.out.println("Por favor introduzca un simple perfil");
        System.out.println("");
        System.out.println("");
        do {//pedir informacion de usuario
            System.out.println("Nombre de Usuario");
            usuario = sc.nextLine();
            System.out.println("Contraseña del usuario");
            contra = sc.nextLine();
            System.out.println("");
        } while (usuario == null && contra == null);//para que no pueda avanzar 
        //hasta que se rellenen los dos variables

        //un "try" para conectar al base de datos de mi MYSQL con el URL
        try ( Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/museo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "Jacob", "jacob")) {
            
            Statement smt = con.createStatement();//creando una declaracion de los intrucciones- 
            //- que voy a ejecutar en el base de datos

            //las instrucciones para meter la informacion del usuario en la tabla de museologin
            smt.executeUpdate("insert into museologin values('" + usuario + "','" + contra + "',NOW())");

            //usando el mismo variable de la declaracion con una nueva para hacer una consulta de la tabla-
            //- de base de datos y ver que informacion hay
            ResultSet rs = smt.executeQuery("select * from museologin");

            while (rs.next()) {//usando el nuevo variable para pedir informacion de la tabla y representarlo-
                //-como String, tambien represento la fecha como String pero lo recojo de la table como un objeto
                System.out.println("\n Nombre de usuario: " + rs.getString("username") + "\n Contraseña: " + rs.getString("contrasena") + "\n Fecha exacta: " + rs.getObject("fecha"));

            }
            rs.close();//finalizo la ejecucion de la variable de la consulta
            smt.close();//finalizo la ejecucion de la variable de la declaracion de mas arriba
            con.close();//finalizo la conxion al base de datos
            new deslizador().setVisible(true);// Abro el interfaz de la exposicion de fotos de animales
        }
    }

}
