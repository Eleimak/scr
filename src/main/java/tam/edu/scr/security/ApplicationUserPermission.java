package tam.edu.scr.security;

public enum ApplicationUserPermission {

    PERSON_READ("person:read"),

    PERSON_WRITE("person:write"),

    DOCTOR_READ("doctor:read"),

    DOCTOR_WRITE("doctor:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
