package com.plate.silverplate.userPhysical.service;

import com.plate.silverplate.user.domain.entity.User;
import com.plate.silverplate.user.repository.UserRepository;
import com.plate.silverplate.userPhysical.domain.dto.UserPhysicalRequest;
import com.plate.silverplate.userPhysical.domain.entity.UserPhysical;
import com.plate.silverplate.userPhysical.repository.UserPhysicalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserPhysicalService {
    private final UserPhysicalRepository userPhysicalRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createUserPhysical(UserPhysicalRequest physicalRequest, User user) {
        UserPhysical userPhysical = UserPhysical.builder()
                .gender(physicalRequest.gender())
                .weight(physicalRequest.weight())
                .height(physicalRequest.height())
                .age(physicalRequest.age())
                .activityCoefficient(physicalRequest.activityCoefficient())
                .build();

        UserPhysical createdUserPhysical = userPhysicalRepository.save(userPhysical);

        user.updateUserPhysical(createdUserPhysical);
        userRepository.save(user);

        return createdUserPhysical.getId();
    }
}
