package com.example.demo;

import java.util.ResourceBundle;

public enum FxmlView {


     
	  LOGIN {
	        @Override
	        String getTitle() {
	            return getStringFromResourceBundle("login.title");
	        }


	        @Override
	        String getFxmlFile() {
	            return "/fxml/login.fxml";
	        }
	    }

    ;
    
    abstract String getTitle();
    abstract String getFxmlFile();


    String getStringFromResourceBundle(String key)
    {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
