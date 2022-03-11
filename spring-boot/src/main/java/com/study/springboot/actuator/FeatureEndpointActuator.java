package com.study.springboot.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpointActuator {
    private final Map<String, Feature> features = new ConcurrentHashMap<>();

    private record Feature(boolean isEnabled){}

    public FeatureEndpointActuator(){
        features.put("Department", new Feature(true));
        features.put("User", new Feature(false));
        features.put("Authentication", new Feature(false));
    }

    @ReadOperation
    public Map<String, Feature> getFeatures(){
        return features;
    }

    @ReadOperation
    public Feature feature(@Selector String featureName) {
        return features.get(featureName);
    }

}

