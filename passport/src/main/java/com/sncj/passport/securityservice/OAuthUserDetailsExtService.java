package com.sncj.passport.securityservice;

import com.sncj.passport.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by momo on 2017/5/8.
 */
@Service
public class OAuthUserDetailsExtService {

    private static final String GET_USERS = "SELECT id, username, password, mobile, email, name, enabled, expired, locked, limited, time FROM t_user WHERE deleted=FALSE ORDER BY id DESC";
    private static final String ADD_USER_DETAILS = "INSERT INTO t_user (username,password) VALUES (?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private OAuthUserDetailsService userDetailsService;

    public List<UserEntity> getUsers() {

        return jdbcTemplate.query(GET_USERS, (rs, rowNum) -> mapRow(rs));
    }

    private UserEntity mapRow(ResultSet rs) throws SQLException {

        return new UserEntity(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("mobile"),
                rs.getString("email"),
                rs.getString("name"),
                rs.getBoolean("enabled"),
                rs.getBoolean("expired"),
                rs.getBoolean("locked"),
                rs.getBoolean("limited"),
                rs.getTimestamp("time"),
                userDetailsService.getAuthoritiesByUsername(rs.getString("username"))
        );
    }

    public void addUser(UserEntity userDetails) {
        Assert.notNull(userDetails, "no user id");

        Object[] fields = getFields(userDetails);
        addUserCycle(fields);

    }
    private void addUserCycle(Object[] fields){
        try {
            jdbcTemplate.update(ADD_USER_DETAILS, fields);
        } catch (Exception e) {
            addUserCycle(fields);
        }
    }
    private Object[] getFields(UserEntity userDetails) {
        return new Object[]{
                userDetails.getUsername()!=null?userDetails.getUsername():null,
                userDetails.getPassword()!=null?userDetails.getPassword():null
        };
    }

}
