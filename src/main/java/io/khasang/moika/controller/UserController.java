package io.khasang.moika.controller;

import io.khasang.moika.entity.User;
import io.khasang.moika.service.UserService;
import io.khasang.moika.util.BindingResultToMapParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * Контроллер интерфейсов пользователя
 *
 * @author Rostislav Dublin, Kovalev Aleksandr
 * @since 2017-03-01
 */

@RequestMapping(path = "/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

   @Autowired
   private Validator mvcValidator;


    private User getCurrentUser() {
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
            return userService.findByLogin(currentLogin);

    }


/*    @RequestMapping("/createTestUser")
    public String createTestUser(Model model) {
        return createUser(model,
                "TestUser-" + DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss").format(LocalDateTime.now()),
                "123456Qw",
                "Тест",
                "Тестович",
                "Тестов",
                "test@mail.ru"
        );
    }
*/

    @RequestMapping(value = "/reg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object createUser(@RequestBody @Valid User user, BindingResult result) {
        //mvcValidator.validate(user,result);
        if(result.hasErrors()){
            return BindingResultToMapParser.getMap(result);
        }
        userService.createUser(user);
        return BindingResultToMapParser.getSuccess("All good!!! =)");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object loginUser(@RequestBody Map<String, Object> user, BindingResult result) {
        User currentUser = getCurrentUser();
        if(currentUser!=null){
            return null;
        }
        
        mvcValidator.validate(user,result);
        if(result.hasErrors()){
            return BindingResultToMapParser.getMap(result);
        }
        userService.createUser(user);
        return BindingResultToMapParser.getSuccess("All good!!! =)");
    }



}
