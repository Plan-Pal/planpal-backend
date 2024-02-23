package com.planpal.demo.web.controller;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.converter.FriendConverter;
import com.planpal.demo.converter.UserConverter;
import com.planpal.demo.service.friend.FriendQueryService;
import com.planpal.demo.web.dto.UserResponseDto.GetResultDto;
import com.planpal.demo.web.dto.friend.FriendResponseDto.GetReceivedFriendRequestDto;
import com.planpal.demo.web.dto.friend.FriendResponseDto.GetSentFriendRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendGetController {

    private final FriendQueryService friendQueryService;

    @GetMapping("/request/sent")
    public ApiResponse<List<GetSentFriendRequestDto>> getSentFriendRequests(@AuthenticationPrincipal Long userId) {
        List<GetSentFriendRequestDto> getRequestDtos = friendQueryService.getSentFriendRequests(userId).stream()
                .map(FriendConverter::toGetSentFriendRequestDto)
                .toList();
        return ApiResponse.onSuccess(getRequestDtos);
    }

    @GetMapping("/request/received")
    public ApiResponse<List<GetReceivedFriendRequestDto>> getReceivedFriendRequests(@AuthenticationPrincipal Long userId) {
        List<GetReceivedFriendRequestDto> getRequestDtos = friendQueryService.getReceivedFriendRequests(userId).stream()
                .map(FriendConverter::toGetReceivedFriendRequestDto)
                .toList();
        return ApiResponse.onSuccess(getRequestDtos);
    }

    @GetMapping
    public ApiResponse<List<GetResultDto>> getFriends(@AuthenticationPrincipal Long userId) {
        List<GetResultDto> getResultDtos = friendQueryService.getFriends(userId).stream()
                .map(UserConverter::toGetResultDto)
                .toList();
        return ApiResponse.onSuccess(getResultDtos);
    }
}
