import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Author:guang yong
 * Description:
 * @Date:Created in 10:33 2018/8/20
 * @Modified By:
 */
public class JdbcRealmTest {

    //使用druid连接池做测试
    DruidDataSource dataSource = new DruidDataSource();
    {
        dataSource.setUrl("jdbc:oracle:thin:@10.0.0.157:1521:activiti");
        dataSource.setUsername("activiti");
        dataSource.setPassword("feizhu");

    }
    @Test
    public void testJdbcRealm(){

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        //查询权限开关，允许查询权限
        jdbcRealm.setPermissionsLookupEnabled(true);

        //使用自己的sql
        String sql = "select password from t_user where user_name = ?";
        jdbcRealm.setAuthenticationQuery(sql);
        String roleSql = "select role_name from t_role where user_id =?";
        jdbcRealm.setUserRolesQuery(roleSql);

        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        //jdbcRealm会使用默认的sql语句去查询数据库数据
        /**
         * protected static final String DEFAULT_AUTHENTICATION_QUERY = "select password from users where username = ?";
            protected static final String DEFAULT_SALTED_AUTHENTICATION_QUERY = "select password, password_salt from users where username = ?";
            protected static final String DEFAULT_USER_ROLES_QUERY = "select role_name from user_roles where username = ?";
            protected static final String DEFAULT_PERMISSIONS_QUERY = "select permission from roles_permissions where role_name = ?";
         */
        UsernamePasswordToken token = new UsernamePasswordToken("test","123456");
        subject.login(token);
        System.out.println(subject.isAuthenticated());

    }
}
