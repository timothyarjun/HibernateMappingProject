package com.spring.project.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.entity.OtpSystem;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class OtpRestController {
	private Map<String , OtpSystem> otp_data= new HashMap<String, OtpSystem>();
	private final static String ACCOUNT_SID="xxxxxxxxxxxxxxxxxxxxxxxx";
	private final static String AUTH_TOKEN="xxxxxxxxxxxxxxxxxxxxxx";
	
	static {
	 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}
	
	@PostMapping("/mobilenumbers/{mobilenumber}/otp")
	public ResponseEntity<Object> sendOTP(@PathVariable("mobilenumber") String mobilenumber){
		OtpSystem otpSystem=new OtpSystem();		
		otpSystem.setMobilenumber(mobilenumber);
		otpSystem.setOtp(String.valueOf(((int)(Math.random()*(1000000-100000)))+1000));
		otpSystem.setExpirytime(System.currentTimeMillis()+30000);
		otp_data.put(mobilenumber, otpSystem);
		Message.creator(new PhoneNumber("+918778926957"), new PhoneNumber("+13343101308"), "Your OTP is "+ otpSystem.getOtp()).create();
		System.out.println(otpSystem.getExpirytime());
		return new ResponseEntity<>("Your OTP is send successfully", HttpStatus.OK);
	}
	
	@PutMapping("/mobilenumbers/{mobilenumber}/otp")
	public ResponseEntity<Object> verifyOTP(@PathVariable("mobilenumber") String mobilenumber, @RequestBody OtpSystem otpSystem){
		
		if(otpSystem.getOtp()==null || otpSystem.getOtp().trim().length()<=0) {
			return new ResponseEntity<>("Please Provide OTP", HttpStatus.BAD_REQUEST);
		}
		
		if(otp_data.containsKey(mobilenumber)) {
			OtpSystem otpSystem1 = otp_data.get(mobilenumber);
			if(otpSystem1!=null) {
				System.out.println(otpSystem.getExpirytime());
				if(otpSystem1.getExpirytime()>=System.currentTimeMillis()) {
					if(otpSystem1.getOtp().equals(otpSystem.getOtp())) {
						otp_data.remove(mobilenumber);
						return new ResponseEntity<>("OTP is verified successfully...", HttpStatus.OK);
					}
					return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<>("OTP is expired", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Something went wrong...!!",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Mobile number not found", HttpStatus.NOT_FOUND);
	}
}
