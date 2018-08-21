import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author:guang yong
 * Description:shiro授权
 * @Date:Created in 9:51 2018/8/20
 * @Modified By:
 */
public class AuthorizerTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("test","123456","admin","user");
    }

    @Test
    public void testAuthorizer(){
        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("test","123456");
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        //检查是否有角色
        subject.checkRole("admin");
        subject.checkRoles("admin","user");
    }
}
