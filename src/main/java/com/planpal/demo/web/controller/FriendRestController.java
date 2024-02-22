package com.planpal.demo.web.controller;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.converter.FriendConverter;
import com.planpal.demo.service.friend.FriendQueryService;
import com.planpal.demo.web.dto.friend.FriendResponseDto.GetResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendRestController {

    private final FriendQueryService friendQueryService;

    @GetMapping
    public ApiResponse<List<GetResultDto>> getUsersByNickname(@RequestParam String name) {
        List<GetResultDto> getResultDtos = friendQueryService.getUsersByNickname(name).stream()
                .map(FriendConverter::toGetResultDto)
                .toList();
        return ApiResponse.onSuccess(getResultDtos);
    }
}
