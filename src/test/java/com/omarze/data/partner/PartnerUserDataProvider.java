package com.omarze.data.partner;


import com.github.javafaker.Faker;
import com.omarze.entities.Partner;
import com.omarze.entities.PartnerUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
@RequiredArgsConstructor
public class PartnerUserDataProvider {


    final Faker faker;


    public PartnerUser newEntity(Partner partner) {
        PartnerUser partnerUser = new PartnerUser();

        partnerUser.setPartner(partner);
        partnerUser.setEmail(faker.internet().emailAddress());
        partnerUser.setName("Test Partner");
        partnerUser.setUsername(faker.code().isbn10());
        partnerUser.setPassword(faker.internet().password());

        return partnerUser;
    }



}
