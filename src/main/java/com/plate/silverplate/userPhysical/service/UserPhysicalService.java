package com.plate.silverplate.userPhysical.service;

import com.plate.silverplate.common.exception.ErrorCode;
import com.plate.silverplate.common.exception.ErrorException;
import com.plate.silverplate.user.domain.entity.User;
import com.plate.silverplate.userPhysical.domain.dto.request.UserPhysicalRequest;
import com.plate.silverplate.userPhysical.domain.entity.UserPhysical;
import com.plate.silverplate.userPhysical.repository.UserPhysicalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserPhysicalService {
    private final UserPhysicalRepository userPhysicalRepository;

    @Transactional
    public UserPhysical createUserPhysical(UserPhysicalRequest physicalRequest, User user) {
        UserPhysical userPhysical = UserPhysical.builder()
                .gender(physicalRequest.gender())
                .weight(physicalRequest.weight())
                .height(physicalRequest.height())
                .age(physicalRequest.age())
                .activityCoefficient(physicalRequest.activityCoefficient())
                .user(user)
                .build();

        return userPhysicalRepository.save(userPhysical);
    }

    @Transactional(readOnly = true)
    public UserPhysical getUserPhysical(User user) {
        return findByUser(user);
    }

    @Transactional
    public UserPhysical updateUserPhysical(UserPhysicalRequest physicalRequest, User user) {
        UserPhysical userPhysical = findByUser(user);

        userPhysical.updatePhysicalInfo(physicalRequest);

        return userPhysical;
    }

    @Transactional(readOnly = true)
    public UserPhysical findByUser(User user) {
        return userPhysicalRepository.findUserPhysicalByUser(user)
                .orElseThrow(() -> new ErrorException(ErrorCode.NON_EXISTENT_USER));
    }
}
