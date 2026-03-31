package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {

	public String randomString() {
		
		String generatedstring=RandomStringUtils.randomAlphabetic(10);
		return generatedstring;
	}
	
	public String randomnumeric() {
		String generatednumeric=RandomStringUtils.randomNumeric(10);
		return generatednumeric;
	}
	
	public String randomAlphaNumeric() {
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		String generatednumeric=RandomStringUtils.randomNumeric(3);

		return (generatedstring+generatednumeric);

	}
}
