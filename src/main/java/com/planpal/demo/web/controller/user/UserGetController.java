package com.planpal.demo.web.controller.user;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.converter.UserConverter;
import com.planpal.demo.service.user.UserQueryService;
import com.planpal.demo.web.dto.user.UserResponseDto.GetResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserGetController {

    private final UserQueryService userQueryService;

    @GetMapping(params = "name")
    public ApiResponse<List<GetResultDto>> getUsersByNickname(@RequestParam("name") String name) {
        List<GetResultDto> getResultDtos = userQueryService.getUsersByNickname(name).stream()
                .map(UserConverter::toGetResultDto)
                .toList();
        return ApiResponse.onSuccess(getResultDtos);
    }

    @GetMapping(params = "id")
    public ApiResponse<List<GetResultDto>> getUsersByTagId(@RequestParam("id") String id) {
        List<GetResultDto> getResultDtos = userQueryService.getUsersByTagId(id).stream()
                .map(UserConverter::toGetResultDto)
                .toList();
        return ApiResponse.onSuccess(getResultDtos);
    }
}
