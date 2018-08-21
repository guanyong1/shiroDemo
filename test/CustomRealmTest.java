import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Author:guang yong
 * Description:自定义realm
 * @Date:Created in 11:26 2018/8/20
 * @Modified By:
 */
public class CustomRealmTest {

    @Test
    public void testCustomRealm(){

        CustomRealm customRealm = new CustomRealm();
        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //加密方式
        matcher.setHashAlgorithmName("md5");
        //加密次数
        matcher.setHashIterations(1);
        //进行加密
        customRealm.setCredentialsMatcher(matcher);


        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("test","123456");
        subject.login(token);
        System.out.println(subject.isAuthenticated());
//        subject.checkRole("admin");
//        subject.checkPermission("user:add");
    }
}
