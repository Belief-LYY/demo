package com.example.shiro;

import com.example.demo.util.SecurityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

public class ShiroTest {

    public static Logger logger = LogManager.getLogger();

    @Test
    public void testAuthorizationCustomRealm() {

        Subject currentUser = SecurityManager.currentUser();

        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            logger.info("Retrieved the correct value! [" + value + "]");
        }

        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("296293760", "LYY1996*");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                logger.info("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                logger.info("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                logger.info("The account for username " + token.getPrincipal() + " is locked.  "
                        + "Please contact your administrator to unlock it.");
            } catch (AuthenticationException ae) {
                logger.debug("unexpected condition? error?" + token.getPrincipal());
            }
        }

        logger.info("认证状态：" + currentUser.isAuthenticated());

        logger.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

        currentUser.logout();
    }
}
