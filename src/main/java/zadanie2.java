/*
 * Napisz program który dopisze do Produktów o id 1,3,5 frazę " - BRAK" -
 * skorzystaj z sparametryzowanego PreparedStatement. Wyświetl ile rekordów zostało zaktualizowanych
 * */


import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class zadanie2 {

    public static void main(String[] args) {
        int[] tablica = new int[]{1, 3, 5};

        StringBuilder builder = new StringBuilder("?");
        String questionMark = ",?";

        for (int i = 1; i < tablica.length; i++) {
            builder.append(questionMark);
        }

        String query = "UPDATE product SET PRO_DESCRIPTION = CONCAT(PRO_DESCRIPTION, ?) WHERE PRO_ID IN(" + builder.toString() + ")";

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {

            statement.setString(1, " - BRAK");

            for (int i = 0; i < tablica.length; i++) {
                statement.setInt(i + 2, tablica[i]);
            }

            int countRecords = statement.executeUpdate();

            System.out.println("Zaktualizowano " + countRecords + " rekordów!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String selectQuery = "select * from product";
        try(PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(selectQuery)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("PRO_ID");
                String name = resultSet.getString("PRO_NAME");
                String desc = resultSet.getString("PRO_DESCRIPTION");
                System.out.println("Product Id: "+id+ " Procuct Name: "+name+ " Description "+desc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
