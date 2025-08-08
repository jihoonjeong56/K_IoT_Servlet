package org.example.k4_iot_servlet.dao;

import org.example.k4_iot_servlet.db.DBConnection;
import org.example.k4_iot_servlet.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Data Access Object : DB의 데이터에 접근하기 위한 객체
public class UserDao {
    // 1. CREATE
    public void insertUser (User user) throws SQLException{
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UserSql.INSERT)
        ){
            pstmt.setString(1,user.getName());
            pstmt.setString(2,user.getEmail());
            pstmt.setString(3,user.getCountry());
            pstmt.executeUpdate();
        }
    }
    //2. READ (단건)
    public static User selectUserById(int id) throws SQLException{
        User user = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UserSql.SELECT_BY_ID)
        ){
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                user = new User(
                        id,
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("country")
                );
            }
        }
        return user;
    }
    // 3. READ (전체)
    public List<User> selectAllUsers()  throws SQLException{
        List<User>users = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UserSql.SELECT_ALL);
             ResultSet rs = pstmt.executeQuery()
             ){
            while (rs.next()){
                users.add(new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("country")
                        ));
            }
        }
        return users;
    }
    // 4. UPDATE
    public boolean updateUser(User user) throws SQLException{
        boolean rowUpdated;
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(UserSql.UPDATE)){
            pstmt.setString(1, user.getName());
            pstmt.setString(2,user.getEmail());
            pstmt.setString(3,user.getCountry());
            pstmt.setInt(4,user.getId());

            rowUpdated = pstmt.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    //5. DELETE
    public boolean deleteUser(int id) throws SQLException{
        boolean rowDeleted;
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(UserSql.DELETE)){
            pstmt.setInt(1,id);
            rowDeleted = pstmt.executeUpdate() > 0;
        }
        return rowDeleted;
    }

}
