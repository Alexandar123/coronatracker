package io.agordic.coronavirustracker.roles;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRoles() {
        return role;
    }

    public static String safeValueOf(String name) {
        if (name == null) {
            return "UNKNOWN";
        }
        return UserRole.valueOf(name).role;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
