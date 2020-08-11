package ru.reidj.springapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.reidj.springapplication.service.UserService;
import ru.reidj.springapplication.user.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Controller
public class RegistrationController {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) throws IOException {
        if (!userService.addUser(user)) {
            model.put("message", "Этот пользователь уже зарегистрирован!");
            return "registration";
        }

        File root = new File(uploadPath + "/" + user.getEmail());

        if (!root.exists())
            root.mkdirs();

        File input = new File(root.getAbsolutePath() + "/input.txt");
        input.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(input));
        writer.write("Место жительства: " + user.getLocation() + " ");
        writer.write("Вакансия: " + user.getVacancy());
        writer.flush();
        writer.close();

        input.createNewFile();

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "Вы успешно активировали аккаунт!");
        } else {
            model.addAttribute("message", "Во время активации произошла ошибка!");
        }

        return "login";
    }
}
