package me.berg.forming.web.controller;

import lombok.RequiredArgsConstructor;
import me.berg.forming.service.ProjectResultService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/result/")
@RequiredArgsConstructor
public class ProjectResultController {

    private final ProjectResultService resultService;



}
