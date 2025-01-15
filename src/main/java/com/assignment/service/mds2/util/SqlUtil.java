package com.assignment.service.mds2.util;

import com.assignment.service.mds2.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUtil {
        public static <t>t execute(String sql, Object... params) throws SQLException {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < params.length; i++){
                preparedStatement.setObject(i + 1, params[i]);
            }
            if (sql.startsWith("SELECT")){
                ResultSet resultSet = preparedStatement.executeQuery();
                return (t) resultSet;
            }else{
                int i = preparedStatement.executeUpdate();
                boolean isSaved = i > 0;
                return (t)((Boolean)isSaved);
            }
        }
}
