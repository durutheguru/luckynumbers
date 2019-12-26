package com.omarze.entities;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * created by julian
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthId {


    public String authUsername;


    public String authRoleId;


}
