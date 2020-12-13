package com.julianduru.omarze.services.campaign.email;


import com.julianduru.omarze.entities.Email;
import com.julianduru.omarze.entities.EmailStatus;
import com.julianduru.omarze.entities.EmailType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * created by julian
 */
@Component
public class StageWinnerEmailComposerImpl implements StageWinnerEmailComposer {


    @Value("${lottery.config.email.default-sender:happybot@lucky.ng}")
    private String defaultEmailSender;


    @Override
    public Email composeEmail(StageWinnerEmailRequest request) {
        var email = new Email();

        email.setSender(defaultEmailSender);
        email.setTitle("Congratulations from LuckyYou.NG");
        email.setEmailType(EmailType.TEXT);
        email.setRecipients(
            List.of(request.getLotteryUserCampaign().getLotteryUser().getEmail())
        );
        email.setMessage(
            String.format(
                "Congratulations!!! you were just selected as winner of the %s stage for Lottery %s",
                request.getStage().toString(),
                request.getLotteryUserCampaign().getCampaign().getName()
            )
        );
        email.setEmailStatus(EmailStatus.PENDING_DISPATCH);

        return email;
    }


}


