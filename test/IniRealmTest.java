import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Author:guang yong
 * Description:
 * @Date:Created in 10:20 2018/8/20
 * @Modified By:
 */
public class IniRealmTest {

    @Test
    public void testIniRealm(){

        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("test","123456");
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        //检查时候有角色
        subject.checkRole("admin");
        //检查时候有权限
        subject.checkPermission("user:delete");
    }
}
