package io.springmvc.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/basic")
public class BasicController {
    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        /** HTML 엔티티
         * 웹 브라우저는 `<` 를 HTML 테그의 시작으로 인식한다. 따라서 `<`를 태그의 시작이 아닌 문자로 표현할 수 있는 방법이 필요한데 이것을 HTML 엔티티 라고 한다.
         * HTML에서 사용하는 특수 문자는 HTML 엔티티로 변경하는 것을 이스케이프라고 하며 타입리프가 제공하는, `th:test`, [[...]] 는 기본적 Escape를 제공 */
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-basic";
    }
    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped";
    }
}
