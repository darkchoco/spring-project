package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 예제 5.1
@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    // 예제 5.26
    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);

    // 예제 5.2
    // http://localhost:8080/api/v1/get-api/hello
    // 아래 방식은 GetMapping으로 대체
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        // 예제 5.27
        LOGGER.info("getHello 메소드가 호출되었습니다.");
        return "Hello World";
    }

    // 예제 5.3
    // http://localhost:8080/api/v1/get-api/name
    @GetMapping(value = "/name")
    public String getName() {
        // 예제 5.27
        LOGGER.info("getName 메소드가 호출되었습니다.");
        return "Flature";
    }

    // 예제 5.4
    // http://localhost:8080/api/v1/get-api/variable1/{String 값}
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        // 예제 5.28
        LOGGER.info("@PathVariable을 통해 들어온 값 : {}", variable);
        return variable;
    }

    // 예제 5.5
    // http://localhost:8080/api/v1/get-api/variable2/{String 값}
    // 상기 예제와는 달리 지정한 변수명(var)과 메서드 매개변수 이름(variable)을 다르게 유지할 경우
    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }

    // 예제 5.6, 예제 5.23
    // http://localhost:8080/api/v1/get-api/request2?name=flature&email=thinkground.flature@gmail.com&organization=thinkground
    // 상기 예제는 URL 경로에 값을 담아 보내는 경우고 이 경우는 쿼리 형식(?key=value&...)으로 값을 전달하는 경우
    @ApiOperation(value = "GET 메소드 예제", notes = "@RequestParam을 활용한 GET Method")
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @ApiParam(value = "이름", required = true) @RequestParam String name,
            @ApiParam(value = "이메일", required = true) @RequestParam String email,
            @ApiParam(value = "회사", required = true) @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }

    // 예제 5.7
    // http://localhost:8080/api/v1/get-api/request2?key1=value1&key2=value2
    // 클라이언트 쪽에서 값을 받을 때 필수항목 아닌 선택항목들이 있을 경우 뭐가 올 지 모르므로 Map 객체로 받는다.
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.forEach((key, value) -> sb.append(key).append(" : ").append(value).append("\n"));

        return sb.toString();
    }

    // 예제 5.9
    // http://localhost:8080/api/v1/get-api/request3?name=value1&email=value2&organization=value3
    @ApiOperation(value = "GET 메소드 예제", notes = "DTO를 활용한 GET Method")
    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDTO) {
        return memberDTO.toString();
    }
}
