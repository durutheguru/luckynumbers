package com.omarze.controller.api;


import com.omarze.Constants;
import com.omarze.api.dto.PartnerDTO;
import com.omarze.api.dto.PartnerImageDTO;
import com.omarze.entities.Partner;
import com.omarze.entities.PartnerImage;
import com.omarze.exception.ServiceException;
import com.omarze.security.annotation.IsBackOfficeUser;
import com.omarze.services.partner.PartnerService;
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


    public final static String PATH = Constants.API_BASE + "/partner";


    private PartnerService partnerService;


    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }


//    @PostMapping
//    @ResponseStatus(code = HttpStatus.CREATED)
//    @IsBackOfficeUser
//    public PartnerDTO savePartner(
//            @Valid @RequestBody PartnerDTO partnerDTO
//    ) throws ServiceException {
//        Partner partner = partnerService.savePartner(partnerDTO);
//        return map(partner, PartnerDTO.class);
//    }


//    @PutMapping
//    @IsBackOfficeUser
//    public PartnerDTO updatePartner(
//            @Valid @RequestBody PartnerDTO partnerDTO
//    ) throws ServiceException {
//        Partner partner = partnerService.updatePartner(partnerDTO);
//        return map(partner, PartnerDTO.class);
//    }


    @GetMapping
//    @IsLotteryUser
    public Page<PartnerDTO> getPartners(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) throws ServiceException {
        Page<Partner> partners = partnerService.getPartners(page, size);
        return map(partners, PartnerDTO.class);
    }


//    @GetMapping("/{id}")
//    public PartnerDTO getPartner(@PathVariable Long id) throws ServiceException {
//         return map(partnerService.getPartner(id), PartnerDTO.class);
//    }


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

