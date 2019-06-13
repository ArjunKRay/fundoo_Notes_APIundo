package com.bridgelabz.utility;

import java.io.UnsupportedEncodingException;

public interface ITockenGenerate {
	
	
      String generateTocken(String id) throws IllegalArgumentException,UnsupportedEncodingException;
	
      String verifryTocken();
	 


}
