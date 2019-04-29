package com.example.demo.util;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;

public class SecurityManager {

	static {
		Collection<Realm> realms = new ArrayList<>();
		// Add your realms
		realms.add(new UserRealm());
		DefaultSecurityManager manager = new DefaultSecurityManager(realms);

		// INI配置方式
		// Factory<SecurityManager> factory = new
		// IniSecurityManagerFactory("shiro.ini");
		// SecurityManager manager = factory.getInstance();

		SecurityUtils.setSecurityManager(manager);
	}

	public static Subject currentUser() {
		return SecurityUtils.getSubject();
	}
}
