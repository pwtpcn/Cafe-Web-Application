package ku.cs.cafe.controller;

import jakarta.validation.Valid;
import ku.cs.cafe.request.MenuRequest;
import ku.cs.cafe.service.CategoryService;
import ku.cs.cafe.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getAllMenus(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "menu-all";
    }

    @GetMapping("/add")
    public String getMenuForm(Model model) {
        model.addAttribute("menuRequest", new MenuRequest());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "menu-add";
    }

    @PostMapping("/add")
    public String createMenu(@Valid MenuRequest menu,
                             BindingResult result, Model model) {
        if (result.hasErrors())
            return "menu-add";

        menuService.createMenu(menu);
        return "redirect:/menus";
    }
}
