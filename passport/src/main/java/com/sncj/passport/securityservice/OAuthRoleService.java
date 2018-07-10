package com.sncj.passport.securityservice;

import com.sncj.passport.entity.RoleEntity;
import com.sncj.passport.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOMO-PC on 2017/5/10.
 */
@Service
public class OAuthRoleService {

    private static final String GET_ROLES = "SELECT id, name, description FROM t_role ORDER BY id ASC";
    private static final String GET_COUNT = "SELECT COUNT(*) FROM t_role";
    private static final String GET_AUTHORITY_BY_UID_RID = "SELECT role_id FROM t_user_role WHERE user_id=?";
    private static final String ADD_CLIENT_USER_AUTHORITY = "INSERT INTO t_user_role (user_id,role_id) VALUES (?,?)";
    private static final String REMOVE_AUTHORITY = "DELETE FROM t_user_role WHERE user_id=? AND role_id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<RoleEntity> getRoles() {
        return jdbcTemplate.query(GET_ROLES, (rs, rowNum) -> new RoleEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
    }
    public void addAuthority(UserEntity user, String role) {
        Assert.notNull(user, "no user id");
        Assert.notNull(role, "no role id");
        String[] strings=role.split(",");
        //传进来的角色
        List<String> stringList=new ArrayList<>();
        for (int i=0;i<strings.length;i++) {
            stringList.add(strings[i]);
        }
        //角色总数
        Integer count= jdbcTemplate.queryForObject(GET_COUNT,Integer.class);
        //已有角色
        List<Integer> rids =  jdbcTemplate.queryForList(GET_AUTHORITY_BY_UID_RID, Integer.class,user.getId());
        for(int i = 1; i<=count; i++){
            //如果这个角色已有而且传入已有,什么都不做
            if(stringList.contains(String.valueOf(i))&&rids.contains(i)){
            //如果这个角色被传入,而并不是已有,就增加
            }else if(stringList.contains(String.valueOf(i))){
                Object[] fields = getFieldsForAuthority(user, String.valueOf(i));
                addAuthorityCycle(fields);
            //如果这个角色没被传入,而且是已经有了的情况,就删除
            }else if (rids.contains(i)){
               jdbcTemplate.update(REMOVE_AUTHORITY, user.getId(),i);
            }
        }

    }
    private void addAuthorityCycle(Object[] fields){
        try {
            jdbcTemplate.update(ADD_CLIENT_USER_AUTHORITY, fields);
        } catch (Exception e) {
            addAuthorityCycle(fields);
        }
    }

    private Object[] getFieldsForAuthority(UserEntity user, String role) {
        return new Object[]{
                user.getId()!= null ? user.getId() : null,
                Integer.valueOf(role)
        };
    }

    public List<Integer> getAuthorityById(Integer l) {
        List<Integer> rids =  jdbcTemplate.queryForList(GET_AUTHORITY_BY_UID_RID, Integer.class,l);
        return rids;
    }
}
