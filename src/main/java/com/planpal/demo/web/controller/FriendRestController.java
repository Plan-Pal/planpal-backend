package com.planpal.demo.web.controller;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.service.friend.FriendCommandService;
import com.planpal.demo.web.dto.friend.FriendRequestDto.InviteDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendRestController {

    private final FriendCommandService friendCommandService;

    @PostMapping("/request")
    public ApiResponse<Void> inviteFriend(@AuthenticationPrincipal Long userId,
                                           @RequestBody @Valid InviteDto inviteDto) {
        friendCommandService.inviteFriend(userId, inviteDto);
        return ApiResponse.onSuccess(null);
    }
}
