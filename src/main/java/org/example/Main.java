package org.example;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main( String[] args ) {

        Main pro = new Main();
        pro.createConnection();
    }



    void createConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con =  DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/4win", "klaasql", "4win"   );
            System.out.println( "Database Connection Success" );
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery( "SELECT * FROM Names" );

            while ( result.next() ) {
                String name = result.getString( "name" );
                System.out.println( name );
            }


        }catch ( ClassNotFoundException | SQLException exception) {
            Logger.getLogger( Main.class.getName()).log( Level.SEVERE,null, exception );
        }

    }



}
