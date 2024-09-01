package com.ecommerce.eureka.commands;

import com.ecommerce.eureka.services.EurekaServerService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * This class is responsible for creating commands.
 */
@ShellComponent
public class EurekaCommands {
    /**
     * The group id.
     */
    private static final String GROUP_ID = "Eureka Server";

    /**
     * The Eureka Server service.
     */
    private final EurekaServerService eurekaServerService;

    public EurekaCommands(final EurekaServerService eurekaServerService) {
        this.eurekaServerService = eurekaServerService;
    }

    /**
     * Check the status of the Eureka Server.
     */
    @ShellMethod(key = "check-status", value = "Check the status of the Eureka Server", group = GROUP_ID)
    public String checkStatus() {
        return this.eurekaServerService.checkStatus();
    }

    /**
     * List all registered clients.
     */
    @ShellMethod(key = "list-clients", value = "List all registered clients", group = GROUP_ID)
    public void listRegisteredClients() {
        this.eurekaServerService.listRegisteredClients();
    }
}
