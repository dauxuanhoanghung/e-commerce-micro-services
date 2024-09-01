package com.ecommerce.eureka.services;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is responsible for interacting with Eureka.
 */
@Service
public class EurekaServerService {

    private final EurekaClient eurekaClient;

    /**
     * Constructor to create an instance of EurekaServerService.
     * @param eurekaClient
     */
    public EurekaServerService(final EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    /**
     * List all registered clients.
     */
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

    /**
     * Check the status of the Eureka Server.
     * @return
     */
    public String checkStatus() {
        return "Eureka Server is up and running!";
    }

}
