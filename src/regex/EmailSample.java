package regex;

/*
 * This class is used to store
 * an email address and to store if it is written or not in a valid format;
 * 
 * Example:
 * 
 * EmailSample{
 * email = mario@gmail.com,
 * valid = true
 * }
 * 
 * EmailSample{
 * email = mar@io@gmail.com,
 * valid = false
 * }
 * 
 * These Object will be created manually and will be used
 * to test a regex Pattern validator;
 * 
 */
public class EmailSample {

	private String email;
	private boolean valid;
	
	public EmailSample(String email, boolean valid) {
		super();
		this.email = email;
		this.valid = valid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
