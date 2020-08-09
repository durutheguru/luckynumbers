package com.julianduru.omarze.controller.api;


import com.julianduru.Constants;
import com.julianduru.omarze.api.dto.PartnerImageDTO;
import com.julianduru.omarze.entities.PartnerImage;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.security.annotation.IsBackOfficeUser;
import com.julianduru.omarze.services.partner.PartnerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * created by julian
 */
@RestController
@RequestMapping(PartnerController.PATH)
public class PartnerController extends BaseApiController {


    public static final String PATH = Constants.API_BASE + "/partner";


    private PartnerService partnerService;


    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }


    @IsBackOfficeUser
    @PostMapping("/{id}/image")
    public List<PartnerImageDTO> uploadImages(
            @PathVariable("id") Long partnerId,
            @RequestParam("files") MultipartFile[] files
    ) throws ServiceException {
        List<PartnerImage> images = partnerService.uploadImages(partnerId, files);
        return map(images, PartnerImageDTO.class);
    }


}


