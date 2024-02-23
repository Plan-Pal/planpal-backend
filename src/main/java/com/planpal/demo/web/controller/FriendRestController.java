package com.planpal.demo.web.controller;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.service.friend.FriendCommandService;
import com.planpal.demo.web.dto.friend.FriendRequestDto.FriendDto;
import com.planpal.demo.web.dto.friend.FriendRequestDto.RequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendRestController {

    private final FriendCommandService friendCommandService;

    @PostMapping("/request")
    public ApiResponse<Void> sendFriendRequest(@AuthenticationPrincipal Long userId,
                                               @RequestBody @Valid FriendDto friendDto) {
        friendCommandService.sendFriendRequest(userId, friendDto);
        return ApiResponse.onSuccess(null);
    }

    @DeleteMapping("/request")
    public ApiResponse<Void> deleteFriendRequest(@AuthenticationPrincipal Long userId,
                                                 @RequestBody @Valid RequestDto requestDto) {
        friendCommandService.deleteFriendRequest(userId, requestDto);
        return ApiResponse.onSuccess(null);
    }

    @PostMapping
    public ApiResponse<Void> acceptFriendRequest(@AuthenticationPrincipal Long userId,
                                                 @RequestBody @Valid RequestDto requestDto) {
        friendCommandService.acceptFriendRequest(userId, requestDto);
        return ApiResponse.onSuccess(null);
    }

    @DeleteMapping
    public ApiResponse<Void> deleteFriend(@AuthenticationPrincipal Long userId,
                                          @RequestBody @Valid FriendDto friendDto) {
        friendCommandService.deleteFriend(userId, friendDto);
        return ApiResponse.onSuccess(null);
    }
}
