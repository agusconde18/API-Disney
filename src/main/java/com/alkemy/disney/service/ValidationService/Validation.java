package com.alkemy.disney.service.ValidationService;

import org.springframework.stereotype.Service;

@Service
public class Validation {
    public Boolean validateString(String validate) {
        if(validate.isEmpty()){
            return false;
        }

        return true;
    }
}
