package com.assignment.service.mds2.model;

import com.assignment.service.mds2.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminModel {
    public String getAdminName(String adminId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select name from employee e join admin a on e.employee_Id = a.employee_Id where admin_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, adminId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString("name");
        }
        return null;
    }
}
