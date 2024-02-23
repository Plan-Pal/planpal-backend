package com.planpal.demo.web.controller;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.converter.FriendConverter;
import com.planpal.demo.service.friend.FriendCommandService;
import com.planpal.demo.service.friend.FriendQueryService;
import com.planpal.demo.web.dto.friend.FriendRequestDto.InviteDto;
import com.planpal.demo.web.dto.friend.FriendResponseDto.GetResultDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendRestController {

    private final FriendQueryService friendQueryService;
    private final FriendCommandService friendCommandService;

    @GetMapping(params = "name")
    public ApiResponse<List<GetResultDto>> getUsersByNickname(@RequestParam("name") String name) {
        List<GetResultDto> getResultDtos = friendQueryService.getUsersByNickname(name).stream()
                .map(FriendConverter::toGetResultDto)
                .toList();
        return ApiResponse.onSuccess(getResultDtos);
    }

    @GetMapping(params = "id")
    public ApiResponse<List<GetResultDto>> getUsersByTagId(@RequestParam("id") String id) {
        List<GetResultDto> getResultDtos = friendQueryService.getUsersByTagId(id).stream()
                .map(FriendConverter::toGetResultDto)
                .toList();
        return ApiResponse.onSuccess(getResultDtos);
    }

    @PostMapping("/request")
    public ApiResponse<Void> inviteFriend(@AuthenticationPrincipal Long userId,
                                           @RequestBody @Valid InviteDto inviteDto) {
        friendCommandService.inviteFriend(userId, inviteDto);
        return ApiResponse.onSuccess(null);
    }
}
