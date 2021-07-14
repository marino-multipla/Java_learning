package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

	public static void main(String[] args) throws Exception {

		TestRegex tr = new TestRegex();
		tr.testListEmailValidator();

	}

	public void testSingleEmailValidator() {

		String expressionPlus="[^@^\\s]+@[^@^.^\\s]+\\.[^@^.^\\s]+";
		Pattern pPlus = Pattern.compile(expressionPlus, Pattern.CASE_INSENSITIVE);
		Matcher m = pPlus.matcher("w.sellitto@hot-mail.it");
		boolean matchFound = m.matches();
		System.out.println(matchFound);


	}
	
	public void testListEmailValidator() throws Exception {

		List<EmailSample> listOfEmailSample = new ArrayList();
		
		//Valid emails
		listOfEmailSample.add(new EmailSample("user@domain.com", true));
		listOfEmailSample.add(new EmailSample("user1@domain.com", true));
		listOfEmailSample.add(new EmailSample("user.name@domain.com", true));
						
		//Invalid emails
		listOfEmailSample.add(new EmailSample("", false));
		listOfEmailSample.add(new EmailSample("@##", false));
		listOfEmailSample.add(new EmailSample("@", false));
		listOfEmailSample.add(new EmailSample("user@domaincom", false));
		listOfEmailSample.add(new EmailSample("user#@domain.co.in", false));
		listOfEmailSample.add(new EmailSample("user@domain.co.in", false));
		listOfEmailSample.add(new EmailSample(" user#domain.com", false));
		listOfEmailSample.add(new EmailSample("@y ahoo.com", false));
		listOfEmailSample.add(new EmailSample("@yahoo.co m", false));
		listOfEmailSample.add(new EmailSample("@@yah oo.com", false));
		listOfEmailSample.add(new EmailSample("@yahoo.@com", false));
		listOfEmailSample.add(new EmailSample("@yah@oo.com", false));
		listOfEmailSample.add(new EmailSample("@y ahoo.com", false));

		String regex="[^@^\\s]+@[^@^.^\\s]+\\.[^@^.^\\s]+";
		//String regex = "^(.+)@(.+)$";

		Pattern pattern = Pattern.compile(regex);

		for(EmailSample emailSample : listOfEmailSample){
			if(pattern.matcher(emailSample.getEmail()).matches() != emailSample.isValid()) {
				throw new Exception("Regex not valid for "+emailSample.getEmail());
			}
		}
		
		System.out.println("All EmailSamples are verified correclty");

	}

	public void testSingleRegex() {

		String expressionPlus="\\w";
		Pattern pPlus = Pattern.compile(expressionPlus, Pattern.CASE_INSENSITIVE);
		Matcher m = pPlus.matcher("Pa");
		boolean matchFound = m.matches();
		System.out.println(matchFound);	

	}

	public void testEmailRegex() {

		List<String> emails = new ArrayList();
		emails.add("  user@domain.com");
		emails.add("user@domain.co.in");
		emails.add("user1@domain.com");
		emails.add("user.name@domain.com");
		emails.add("user#@domain.co.in");
		emails.add("user@domaincom");

		//Invalid emails
		emails.add("user#domain.com");
		emails.add("@yahoo.com");

		String regex = "^(.+)@(.+)$";

		Pattern pattern = Pattern.compile(regex);

		for(String email : emails){
			Matcher matcher = pattern.matcher(email);
			System.out.println(email +" : "+ matcher.matches());
		}

	}

	public void regexBasics() {

		/*
		 * IMPORTANT:
		 * in Java you have to write \\ instead of one \
		 */
		
		/*
		 * literal are as words.
		 * metacharacter are as grammars.
		 * "The words are combined with grammar according to a set of rules in order
		 * to create an expression that communicates an idea"
		 */

		/*
		 * ^ is the caret and match the position at the beginning of the line.
		 * example: ^cat matches only if the cat is at the beginning like category or catalogue;
		 */

		/*
		 * $ is the dollar and match the position at the end of the line.
		 * example: $cat macthes only if the cat is at the end of the line like scat;
		 */

		/*
		 * [...] is called character class and let us list the characters we want to allow at that point in the match;
		 * example: sep[ea]r[ea]te allows seperete, seperate, separete, separate;
		 */

		/*
		 * - is called dash and indicates a ranges of characters.
		 * a dash is considered a metacharacter only if it is inside a character class,
		 * otherwise it matches the normal dash character;
		 * example: [1-6]
		 */

		/*
		 * ^ is the negation sign only if it is inside a character class.
		 * So [^1-6] matches any character that isn't listed so through 1 to 6;
		 * 
		 * ^[1-6] matches the listed characters at the starting point of a line;
		 */

		/*
		 * . is the dot and is used to match any character when it is used OUTSIDE
		 * the character class;
		 * example 24.08.21 matches 24-08-21 and 24/08/21 and 24.08.21
		 * 
		 * when it is used inside character class is not considered as metacharacter.
		 * example: 24[.]08[.]2021 matches only 24.08.21
		 */

		/*
		 * | is the pipe and is used to combine multiple expressions into a single expression.
		 * example: (fir|1)st matches both first and 1st;
		 * 
		 *  when it is used inside character class is not considered as metacharacter.
		 */

		/*
		 * Usage is Java
		 * Pattern p = Pattern.compile("abc");
		 * Matcher m = p.matcher("abcabcabcd");
		 * boolean b = m.matches();
		 * 
		 * Pattern is the compiled representation of a regular expression;
		 * Matcher is used to match lines against regular expressions;
		 */

		/*
		 * Whitespace characters are:
		 * 
		 * It is a Unicode space character (SPACE_SEPARATOR, LINE_SEPARATOR, or PARAGRAPH_SEPARATOR) but is not also a non-breaking space ('\u00A0', '\u2007', '\u202F').
		 * It is '\u0009', HORIZONTAL TABULATION.
		 * It is '\u000A', LINE FEED.
		 * It is '\u000B', VERTICAL TABULATION.
		 * It is '\u000C', FORM FEED.
		 * It is '\u000D', CARRIAGE RETURN.
		 * It is '\u001C', FILE SEPARATOR.
		 * It is '\u001D', GROUP SEPARATOR.
		 * It is '\u001E', RECORD SEPARATOR.
		 * It is '\u001F', UNIT SEPARATOR.
		 */

		/*
		 * Java regex metacharacters
		 * \d is any digits, short of [0-9]
		 * \D is any non-digits, short for [^0-9]
		 * \s is any whitespace character, short for [\t\n\x0B\f\r]
		 * \S is any non whitespace character, short [^\s]
		 * \w is any word character, short for [a-zA-Z_0-9]
		 * \W is any non word character, short for [^\w]
		 * \b a word boundary
		 * \B non a word boundary
		 */
		
		/*
		 * x? x occurs once or not at all
		 * X* X occurs zero or more times
		 * X+ X occurs one or more times
		 * X{n} X occurs exactly n times
		 * X{n,} X occurs n or more times
		 * X{n,m} X occurs at least n but not more than m times
		 */
		
	}

}
