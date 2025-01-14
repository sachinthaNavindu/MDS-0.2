package com.assignment.service.mds2.model;

import com.assignment.service.mds2.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CredentialModel {

    public boolean loginCheck(String userName,String password) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT AES_DECRYPT(password,'your_secret_key')AS decrypted_password FROM credential2 WHERE userName=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,userName);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String decryptedPassword = resultSet.getString("decrypted_password");
            boolean isPasswordCorrect = password.equals(decryptedPassword);
            return isPasswordCorrect;
        }else{
            return false;
        }
    }

    public ArrayList<String> loadAdminIdEmptyUser() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT a.admin_id FROM admin a LEFT JOIN credential c ON a.admin_id = c.admin_id WHERE c.admin_id IS NULL";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ArrayList<String> adminIdEmptyUser = new ArrayList<>();

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                adminIdEmptyUser.add(resultSet.getString("admin_id"));
            }
            return adminIdEmptyUser;
    }
}



//SELECT a.admin_id
//FROM admin a
//LEFT JOIN credential c
//ON a.admin_id = c.admin_id
//WHERE c.admin_id IS NULL;