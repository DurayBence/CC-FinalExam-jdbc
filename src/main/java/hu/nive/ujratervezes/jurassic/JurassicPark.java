package hu.nive.ujratervezes.jurassic;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        List<String> overpopulatedSpeciesList = new ArrayList<>();

        try {
            String SQL = "SELECT breed FROM dinosaur " +
                    "WHERE actual > expected " +
                    "ORDER BY breed ASC";
            PreparedStatement pst = DriverManager.getConnection(dbUrl, dbUser, dbPassword).prepareStatement(SQL);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                overpopulatedSpeciesList.add(rs.getString("breed"));
            }

            rs.close();
            pst.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return overpopulatedSpeciesList;
    }

}
