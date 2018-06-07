package com.redhat.training.msa.hello;

//TODO annotate as a Spring framework managed bean
public class BonjourServiceImpl implements BonjourService {

    //TODO annotate to inject the com.redhat.training.msa.hello.show-location system property
    private boolean showLocation;

    @Override
    public String bonjour(String location) {
    	if (showLocation)
    		return "Bonjour de " + location;
    	else
    		return "Bonjour";
    }
}
