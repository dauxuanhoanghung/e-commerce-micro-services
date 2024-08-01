package com.ecommerce.eureka.commands;

import com.ecommerce.eureka.services.EurekaServerService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class EurekaCommands {

    private static final String GROUP_ID = "Eureka Server";
    private final EurekaServerService eurekaServerService;
    public EurekaCommands(EurekaServerService eurekaServerService) {
        this.eurekaServerService = eurekaServerService;
    }

    @ShellMethod(key = "check-status", value = "Check the status of the Eureka Server", group = GROUP_ID)
    public String checkStatus() {
        return this.eurekaServerService.checkStatus();
    }

    @ShellMethod(key = "list-clients", value = "List all registered clients", group = GROUP_ID)
    public void listRegisteredClients() {
        this.eurekaServerService.listRegisteredClients();
    }
}
