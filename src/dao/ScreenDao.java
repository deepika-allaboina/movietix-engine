package dao;

import util.DBConnection;
import dto.Screen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScreenDao {

    public void insertScreen(Screen screen) {
        String sql = "INSERT INTO screens (theater_id, screen_number, capacity) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, screen.getTheaterId());
            pstmt.setInt(2, screen.getScreenNumber());
            pstmt.setInt(3, screen.getCapacity());

            pstmt.executeUpdate();
            System.out.println("✅ Screen inserted.");
        } catch (Exception e) {
            System.out.println("❌ Error inserting screen: " + e.getMessage());
        }
    }

    public List<Screen> getAllScreens() {
        List<Screen> screens = new ArrayList<>();
        String sql = "SELECT * FROM screens";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int screenId = rs.getInt("screen_id");
                int theaterId = rs.getInt("theater_id");
                int screenNumber = rs.getInt("screen_number");
                int capacity = rs.getInt("capacity");

                screens.add(new Screen(screenId, theaterId, screenNumber, capacity));
            }

        } catch (Exception e) {
            System.out.println("❌ Error fetching screens: " + e.getMessage());
        }

        return screens;
    }
}
