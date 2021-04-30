package gg.jominsubyungsin.lib;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Hash {
	public String hashText(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(text.getBytes(), 0, text.getBytes().length);
			return new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "암호화 실패");
		}
	}
}
