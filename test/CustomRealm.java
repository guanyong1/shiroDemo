import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author:guang yong
 * Description:自定义realm
 * @Date:Created in 11:13 2018/8/20
 * @Modified By:
 */
public class CustomRealm extends AuthorizingRealm {

    Map<String,String> userMap = new HashMap<>();
    {
        userMap.put("test","f51703256a38e6bab3d9410a070c32ea");
        super.setName("customRealm");
    }
    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionsByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从主体传过来的认证信息中，获得用户名
        String userName =(String) authenticationToken.getPrincipal();
        //通过用户名到数据库获取凭证
        String pwd = getPwdByUserName(userName);
        if(pwd == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("test",pwd,"customRealm");
        //加盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("salt"));
        return authenticationInfo;
    }

    //模拟访问数据库获取用户信息
    private String getPwdByUserName(String userName){
        return userMap.get(userName);
    }

    //模拟访问数据库或缓存中获取角色信息
    private Set<String> getRolesByUserName(String userName){
        Set<String> set = new HashSet<>();
        set.add("admin");
        set.add("user");
        return set;
    }

    //模拟访问数据库或缓存中获取权限信息
    private Set<String> getPermissionsByUserName(String userName){
        Set<String> set = new HashSet<>();
        set.add("user:delete");
        set.add("user:add");
        return set;
    }

    //获取密文
    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456","salt");
        System.out.println(md5Hash.toString());
    }
}
