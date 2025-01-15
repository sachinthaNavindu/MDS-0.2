package com.assignment.service.mds2.model;

import com.assignment.service.mds2.db.DBConnection;
import com.assignment.service.mds2.dto.CredentialDto;
import com.assignment.service.mds2.util.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CredentialModel {

        public boolean loginCheck(String userName,String password) throws SQLException {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT AES_DECRYPT(password,'your_secret_key')AS decrypted_password FROM credential WHERE userName=?";
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

    public boolean saveNewUserAdmin(CredentialDto credentialDto) throws SQLException {
        String sql = "INSERT INTO credential (userName, admin_id, password, recover_Email) VALUES (?, ?, AES_ENCRYPT(?, ?), ?)";
        return SqlUtil.execute(sql,
                    credentialDto.getUsername(),
                    credentialDto.getAdminId(),
                    credentialDto.getPassword(),
                    "your_secret_key",
                    credentialDto.getRecoverEmail()
                );
    }

    public ArrayList<String> getUserName() throws SQLException {
        ArrayList<String> userName = new ArrayList<>();
        ResultSet resultSet = SqlUtil.execute("SELECT userName FROM credential");
        while (resultSet.next()) {
            userName.add(resultSet.getString("userName"));
        }
        return userName;
    }

    public String getRecoverEmail(String value) throws SQLException {
        ResultSet resultSet = SqlUtil.execute("SELECT recover_Email FROM credential WHERE userName=?", value);
        if (resultSet.next()) {
            return resultSet.getString("recover_Email");
        }
        return null;
    }

    public boolean updatePassword(CredentialDto credentialDto) throws SQLException {
        return SqlUtil.execute("UPDATE credential SET password=AES_ENCRYPT(?,'your_secret_key') WHERE userName=?; ",credentialDto.getPassword(),credentialDto.getUsername());
    }
}

