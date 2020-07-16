package com.omarze.controller.api;


import com.omarze.Constants;
import com.omarze.api.dto.PartnerDTO;
import com.omarze.api.dto.PartnerImageDTO;
import com.omarze.entities.Partner;
import com.omarze.entities.PartnerImage;
import com.omarze.exception.ServiceException;
import com.omarze.security.annotation.IsBackOfficeUser;
import com.omarze.services.partner.PartnerService;
import com.omarze.util.MapperUtil;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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


