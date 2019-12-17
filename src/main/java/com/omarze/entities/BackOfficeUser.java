package com.omarze.entities;


import javax.persistence.Entity;
import java.util.Collections;
import java.util.List;

/**
 * created by julian
 */
@Entity
public class BackOfficeUser extends ApplicationUser {


    public final static String ROLE_ID = "BACK_OFFICE_USER";


    @Override
    protected List<String> roles() {
        return Collections.singletonList(ROLE_ID);
    }


}
