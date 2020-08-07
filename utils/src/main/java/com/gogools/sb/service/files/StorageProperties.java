package com.gogools.sb.service.files;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 *  # max file size
 *	spring.servlet.multipart.max-file-size=10MB #optional
 *  # max request size
 *	spring.servlet.multipart.max-request-size=10MB #optional
 *  # files storage location properties
 *  storage.location=./uploads
 * @author dpulido
 *
 */
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
        
}
