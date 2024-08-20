package com.ecommerce.eureka.services;

import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import org.springframework.stereotype.Service;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import com.netflix.discovery.shared.Application;
import com.netflix.appinfo.InstanceInfo;

import java.util.List;

@Service
public class EurekaServerService {

    private final EurekaClient eurekaClient;

    public EurekaServerService(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    public void listRegisteredClients() {
        PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();

        List<Application> applications = registry.getSortedApplications();

        for (Application app : applications) {
            System.out.println("Application: " + app.getName());
            for (InstanceInfo instanceInfo : app.getInstances()) {
                System.out.println("  Instance ID: " + instanceInfo.getInstanceId());
                System.out.println("  Host: " + instanceInfo.getHostName());
                System.out.println("  Port: " + instanceInfo.getPort());
                System.out.println("  Status: " + instanceInfo.getStatus());
                System.out.println("  ----");
            }
        }
    }

    public String checkStatus() {
        return "Eureka Server is up and running!";
    }


}
