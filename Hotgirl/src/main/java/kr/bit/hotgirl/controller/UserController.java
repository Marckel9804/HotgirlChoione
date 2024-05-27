package kr.bit.hotgirl.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.bit.hotgirl.dto.UserDTO;
import kr.bit.hotgirl.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/User")
@Slf4j
public class UserController {

    private UserService userService;
    private View error;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "User/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "User/register";
        }

        try {
            UserDTO registeredUser = userService.registerUser(userDTO);
            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
            redirectAttributes.addFlashAttribute("userIdx", registeredUser.getUserIdx());
            return "redirect:/User/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/User/register";
        }
    }

    @PostMapping("/user/check-duplication")
    @ResponseBody
    public Map<String, Boolean> checkDuplication(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        boolean isDuplicate = userService.checkDuplicateUserId(userId);
        return Map.of("isDuplicate", isDuplicate);
    }

    @GetMapping("/login")
    public String login() {
        log.info("before go to loginForm");
        return "User/login";
    }

    @PostMapping("/loginProcess")
    public String login(@RequestParam("userId") String userId,
                        @RequestParam("userPw") String userPw,
                        HttpServletRequest request,
                        Model model) {
        log.trace("userId: " + userId + "userPw : " + userPw);

        try {
            UserDTO userDTO = userService.loginUser(userId, userPw);
            HttpSession session = request.getSession();
            session.setAttribute("userId", userDTO.getUserId()); // 세션에 사용자 ID 저장
            log.info("before login");
            return "redirect:/main";
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "User/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/User/login";
    }
    @GetMapping("/mypage")
    public String mypage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false); // 세션 가져오기 (false는 세션이 없으면 null 반환)
        if (session != null) {
            String userId = (String) session.getAttribute("userId"); // 세션에서 userId 가져오기
            if (userId != null) {
                UserDTO userDTO = userService.getUserById(userId);
                model.addAttribute("userDTO", userDTO); // 템플릿에 사용자 정보 전달
            }
        }
        return "User/mypage"; // 템플릿 파일 이름 (mypage.html)
    }

    @GetMapping("/userEdit")
    public String editForm(Principal principal, Model model) {
        if(principal== null) {
            return "redirect:/User/login";
        }
        try{
            String userId = principal.getName();
            UserDTO userDTO = userService.getUserById(userId);
            model.addAttribute("userDTO", userDTO);
            return "User/userEdit";
        }catch(IllegalArgumentException e){
            // 사용자 정보가 없는 경우 처리
            model.addAttribute("error", "회원 정보를 찾을 수 없습니다.");
            return "error"; //
        }
    }

    @PostMapping("/user/edit")
    public String editUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "User/userEdit";
        }

        try {
            userService.updateUser(userDTO);
            redirectAttributes.addFlashAttribute("message", "회원정보가 수정되었습니다.");
            return "redirect:/User/mypage";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "회원정보 수정에 실패했습니다. 다시 시도해주세요.");
            return "redirect:/user/edit";
        }
    }

    @PostMapping("/user/withdrawal")
    public String withdrawalUser(Principal principal) {
        String userId = principal.getName();
        userService.withdrawalUser(userId);
        return "redirect:/User/logout"; // 로그아웃 처리
    }

}
