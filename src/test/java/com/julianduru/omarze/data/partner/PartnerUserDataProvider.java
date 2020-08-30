package com.julianduru.omarze.data.partner;


import com.github.javafaker.Faker;
import com.julianduru.omarze.data.DataProvider;
import com.julianduru.omarze.entities.Partner;
import com.julianduru.omarze.entities.PartnerUser;
import com.julianduru.omarze.persistence.PartnerUserRepository;
import com.julianduru.util.NullAwareBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
@RequiredArgsConstructor
public class PartnerUserDataProvider implements DataProvider<PartnerUser>  {


    final Faker faker;

    final PartnerUserRepository partnerUserRepository;

    final PartnerDataProvider partnerDataProvider;


    public PartnerUser newEntity(Partner partner) {
        PartnerUser partnerUser = new PartnerUser();

        partnerUser.setPartner(partner);
        partnerUser.setEmail(faker.internet().emailAddress());
        partnerUser.setName("Test Partner");
        partnerUser.setUsername(faker.code().isbn10());
        partnerUser.setPassword(faker.internet().password());

        return partnerUser;
    }

    @Override
    public JpaRepository<PartnerUser, Long> getRepository() {
        return partnerUserRepository;
    }

    @Override
    public PartnerUser provide() {
        PartnerUser user = new PartnerUser();

        user.setEmail(faker.internet().emailAddress());
        user.setName(faker.name().fullName());
        user.setPassword(faker.internet().password());
        user.setUsername(faker.name().username());
        user.setPartner(partnerDataProvider.getOrSave());

        return user;
    }

    @Override
    public PartnerUser provide(PartnerUser sample) {
        PartnerUser user = provide();
        NullAwareBeanUtils.copy(sample, user);

        return user;
    }


}
