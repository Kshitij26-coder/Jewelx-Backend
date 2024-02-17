package in.jewelx.jewelxbackend.utils;

/*
 * Enum to manage the roles
 * */
public enum RolesEnum {
	O("O"), E("E"), A("A");

	private final String value;

	RolesEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
