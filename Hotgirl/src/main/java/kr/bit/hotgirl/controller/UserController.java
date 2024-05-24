package kr.bit.hotgirl.controller;

import jakarta.validation.Valid; // @Valid 추가
import kr.bit.hotgirl.dto.UserDTO;
import kr.bit.hotgirl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Model 추가
import org.springframework.validation.BindingResult; // BindingResult 추가
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) { // Model 추가
        model.addAttribute("userDTO", new UserDTO()); // 빈 UserDTO 객체 추가
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO, // @Valid 추가
                               BindingResult bindingResult, // BindingResult 추가
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) { // 입력값 검증
            return "register"; // 검증 실패 시 회원가입 페이지로 이동
        }

        try {
            userService.registerUser(userDTO);
            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "회원가입에 실패했습니다. 다시 시도해주세요.");
            return "redirect:/register";
        }
    }

    @PostMapping("/user/check-duplication")
    @ResponseBody
    public Map<String, Boolean> checkDuplication(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        boolean isDuplicate = userService.checkDuplicateUserId(userId);
        return Map.of("isDuplicate", isDuplicate);
    }
}
