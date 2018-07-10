package com.sncj.passport.securityservice;

import com.sncj.passport.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by momo on 2017/4/20.
 */
@Service
public class OAuthUserDetailsService {

    private static final String FIND_USER_BY_USERNAME = "SELECT id, username, mobile, email, name, enabled, expired, locked, limited, time FROM t_user WHERE username=? AND deleted=FALSE";
    private static final String LOAD_USER_BY_USERNAME = "SELECT id, username, password, enabled FROM t_user WHERE username=? AND expired=FALSE AND locked=FALSE AND limited=FALSE AND deleted=FALSE";
    private static final String GET_AUTHORITIES_BY_USERNAME = "SELECT r.name AS authority FROM t_user u, t_role r, t_user_role a WHERE u.id=a.user_id AND r.id=a.role_id AND u.username=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<? extends GrantedAuthority> getAuthoritiesByUsername(String username) {

        if (!StringUtils.hasText(username)) throw new UsernameNotFoundException("please input username");

        List<? extends GrantedAuthority> authorities = new ArrayList<>();

        try {
            return jdbcTemplate.query(GET_AUTHORITIES_BY_USERNAME, (rs, rowNum) -> new SimpleGrantedAuthority(rs.getString("authority")), username);
        } catch (EmptyResultDataAccessException e) {
            //throw new UsernameNotFoundException("No exist authorities by username: " + username);
        }

        return authorities;
    }

    public UserEntity findUserByUsername(String username) throws UsernameNotFoundException {

        if (!StringUtils.hasText(username)) throw new UsernameNotFoundException("please input username");

        List<? extends GrantedAuthority> authorities = getAuthoritiesByUsername(username);

        try {
            return jdbcTemplate.queryForObject(FIND_USER_BY_USERNAME, (rs, rowNum) ->
                            new UserEntity(
                                    rs.getInt("id"),
                                    rs.getString("username"),
                                    "",
                                    rs.getString("mobile"),
                                    rs.getString("email"),
                                    rs.getString("name"),
                                    rs.getBoolean("enabled"),
                                    rs.getBoolean("expired"),
                                    rs.getBoolean("locked"),
                                    rs.getBoolean("limited"),
                                    rs.getDate("time"),
                                    authorities
                            )
                    , username);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("no exist user by username: " + username);
        }
    }
}
