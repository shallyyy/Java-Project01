
public class User {
	private String username; private String nickname; private String password; private String email;

	User(String aUsername, String aNickname, String aPassword, String aEmail) {
		username=aUsername;
		nickname= aNickname;
		password= aPassword;
		email = aEmail;
	}
	public String getUsername()
	{
		return username;
	}
	public String getNickname()
	{
		return nickname	;
	}
	public String getPassword()
	{
		return password;
	}
	public String getEmail()
	{
		return email;
	}
	
}
