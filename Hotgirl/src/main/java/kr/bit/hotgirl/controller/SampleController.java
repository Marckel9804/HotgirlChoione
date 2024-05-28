package kr.bit.hotgirl.controller;

import kr.bit.hotgirl.entity.SampleEntity;
import kr.bit.hotgirl.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/sample")
@Controller
public class SampleController {
    @Autowired
    private SampleService sampleService;

    @RequestMapping(value = "/smain")
    public String smain() {
        return "sample/smain";
    }

    @GetMapping("/insert_test")
    public String insert_test(Model model) {
        model.addAttribute("sample", new SampleEntity());
        return "sample/insert_test";
    }

    @PostMapping("/insert_proc")
    public String insert_proc(SampleEntity sample) {
        sampleService.createSample(sample);

        log.info("무야호 인서트 성공!!");

        return "sample/smain";
    }

    @GetMapping("/read_test")
    public String read_test(Model model) {
        Long id = 1L;
        Optional<SampleEntity> sampleOptional = sampleService.getSampleById(id);
        sampleOptional.ifPresent(sample -> model.addAttribute("sampleBean", sample));
        List<SampleEntity> sameList = sampleService.getAllSamples();
        model.addAttribute("sampleListBean", sameList);
        return "sample/read_test";
    }

    @GetMapping("/update_test")
    public String update_test(Model model) {
        model.addAttribute("update", new SampleEntity());
        return "sample/update_test";
    }

    @PostMapping("/update_proc")
    public String update_proc(SampleEntity sample) {
        
        sampleService.updateSampleName(sample.getId(), sample.getName());
        log.info("무야호 업데이트 성공!!");
        return "sample/smain";
    }

    @GetMapping("/naverlogin")
    public String naverlogin() {
        return "login/login_success";
    }

    @GetMapping("/login_test")
    public String login_test() {
        return "login/login_testtest";
    }
}
