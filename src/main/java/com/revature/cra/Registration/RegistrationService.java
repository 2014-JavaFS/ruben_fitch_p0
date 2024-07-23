package com.revature.cra.Registration;

import java.util.List;

/**
 * Handles all the business logic for the RegistrationController class
 * It contains methods that validate any and all information provided for creating and finding course registrations
 */
public class RegistrationService {
    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository){
        this.registrationRepository = registrationRepository;
    }

    /**
     * Retrieves a list of all course registrations
     * @return A list of all the course registrations
     */
    public List<Registration> findAll(){
        return registrationRepository.findAll();
    }
}
