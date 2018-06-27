package com.redhat.training.msa.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BonjourServiceImpl implements BonjourService {

    @Value("${com.redhat.training.msa.hello.show-location:true}")
    private boolean showLocation;

    @Override
    public String bonjour(String location) {
    	if (showLocation)
    		return "Bonjour de " + location;
    	else
    		return "Bonjour";
    }
}
