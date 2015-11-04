package com.company;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by BennettIronYard on 11/4/15.
 */
public class MainTest {
    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./test");
        Main.createTables(conn);
        return conn;
    }
    public void endConnection(Connection conn) throws SQLException {
        java.sql.Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE events");
        conn.close();
    }
    @Test
    public void testEvents() throws SQLException {
        Connection conn = startConnection();
        Main.insertEvent(conn, "This is a test", LocalDateTime.now());
        ArrayList<Event> events = Main.selectEvents(conn);
        endConnection(conn);

        assertTrue(events.size() == 1);
    }


}