package com.omarze.data.partner;


import com.github.javafaker.Faker;
import com.omarze.api.dto.PartnerDTO;
import com.omarze.entities.Partner;
import com.omarze.persistence.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
public class PartnerDataService {

    private Faker faker;

    private PartnerRepository partnerRepository;


    @Autowired
    public PartnerDataService setFaker(Faker faker) {
        this.faker = faker;
        return this;
    }


    @Autowired
    public PartnerDataService setPartnerRepository(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
        return this;
    }


    public PartnerDTO newPartnerDTO() {
        PartnerDTO partnerDTO = new PartnerDTO();

        partnerDTO.name = faker.name().fullName();
        partnerDTO.description = faker.company().name();
        partnerDTO.website = "http://partner.com";

        return partnerDTO;
    }


    public Partner newPartner() {
        Partner partner = new Partner();

        partner.setName(faker.name().fullName());
        partner.setDescription(faker.company().name());
        partner.setWebsite("http://partner.com");

        return partner;
    }


    public Partner savePartner() {
        Partner partner = newPartner();
        return partnerRepository.save(partner);
    }


    public void deletePartner(Partner partner) {
        partnerRepository.delete(partner);
    }


}
