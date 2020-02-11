package com.omarze.services.backoffice;


import com.omarze.api.dto.BackOfficeUserDTO;
import com.omarze.entities.BackOfficeUser;
import com.omarze.exception.EntityNotFoundException;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.BackOfficeUserRepository;
import com.omarze.services.backoffice.handlers.Save;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * created by julian
 */
@Service
@Transactional
@RequiredArgsConstructor
public class BackOfficeUserServiceImpl implements BackOfficeUserService {


    private final PasswordEncoder passwordEncoder;


    private final BackOfficeUserRepository userRepository;


    public long userCount() {
        return userRepository.count();
    }


    public boolean backOfficeUserExists() {
        return userCount() > 0;
    }


    public BackOfficeUser saveBackOfficeUser(BackOfficeUserDTO userDTO) throws ServiceException {
        return Save.builder()
                .userDTO(userDTO)
                .passwordEncoder(passwordEncoder)
                .backOfficeUserRepository(userRepository)
                .build()
                .execute();
    }


    @Override
    public BackOfficeUser getById(Long id) throws EntityNotFoundException {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Back Office User", id));
    }


    @Override
    public Page<BackOfficeUser> getUsers(int page, int size) throws ServiceException {
        return userRepository.findAll(PageRequest.of(page, size, Sort.Direction.DESC, "id"));
    }


}