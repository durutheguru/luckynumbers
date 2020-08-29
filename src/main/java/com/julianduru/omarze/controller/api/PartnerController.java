package com.julianduru.omarze.controller.api;


import com.julianduru.Constants;
import com.julianduru.omarze.api.dto.PartnerImageDTO;
import com.julianduru.omarze.entities.PartnerImage;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.security.annotation.IsBackOfficeUser;
import com.julianduru.omarze.services.partner.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * created by julian
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(PartnerController.PATH)
public class PartnerController extends BaseApiController {


    public static final String PATH = Constants.API_BASE + "/partner";


    private final PartnerService partnerService;


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


