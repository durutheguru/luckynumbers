package com.julianduru.omarze.data.partner;


import com.julianduru.omarze.data.TestDataProvider;
import com.julianduru.omarze.entities.Partner;
import com.julianduru.omarze.persistence.PartnerRepository;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
public class PartnerDataProvider extends TestDataProvider<Partner, PartnerRepository> {


    public PartnerDataProvider(PartnerRepository partnerRepository) {
        super(partnerRepository);
    }


    @Override
    public Partner newEntity() {
        Partner partner = new Partner();

        partner.setName(faker.name().fullName());
        partner.setDescription(faker.company().name());
        partner.setWebsite("http://partner.com");

        return partner;
    }


    public void deletePartner(Partner partner) {
        getRepository().delete(partner);
    }



}
