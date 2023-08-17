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
    public static void main( String[] args ) throws SQLException {
        Connection dataBankConnection = createConnection();
        queryDataBankForName( dataBankConnection );
        insertPlayerData( dataBankConnection );
        createNewGameStatus( dataBankConnection );
        updateGameStatus( dataBankConnection );
        howManyGamesPlayed( dataBankConnection );
    }



    static Connection createConnection( ){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con =  DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/4win", "klaasql", "4win"   );
            System.out.println( "Database Connection Success" );
            return con;
        }catch ( ClassNotFoundException | SQLException exception) {
            Logger.getLogger( Main.class.getName()).log( Level.SEVERE,null, exception );
        }
        return null;
    }

    static String queryDataBankForName( Connection con) throws SQLException {
        String name = "";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery( "SELECT * FROM Names" );

        while ( result.next() ) {
            name = result.getString( "name" );
            System.out.println( name );
        }
        return name;
    }

    static void insertPlayerData(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        statement.executeUpdate("INSERT INTO Usernames (username) VALUES ('nico')");
        statement.executeUpdate("INSERT INTO Email_addresses (email_address) VALUES ('nico.gawron@metro.digital')");
        statement.executeUpdate("INSERT INTO Player (username_id, email_id, password) SELECT LAST_INSERT_ID(), LAST_INSERT_ID(), 'PASSWORD'");
        System.out.println("Successfully Inserted");
    }

    static void createNewGameStatus(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        statement.executeUpdate( "Insert into Games (player1_ID, player2_ID)  VALUES (3, 6);");
        System.out.println( "Successfully created new Game" );
    }

    static void updateGameStatus(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        statement.executeUpdate( "UPDATE Games \n" +
                "SET gameStatus = ' {\"gameMapData\": [   [\"PLAYER_1\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\"],   [\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\"],   [\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\"],   [\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\"],   [\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\"],   [\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\",\"EMPTY\"] ], } ' \n" +
                "WHERE games_ID = 4 " );
        System.out.println( "successfully updated fieldStatus" );
    }

    static void howManyGamesPlayed(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery( "SELECT COUNT(*) AS game_count FROM Games WHERE player1_ID = 3 OR player2_ID = 3;" );
        result.next();
        System.out.println( result.getInt( "game_count" ) );

    }
}


