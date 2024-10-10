package ku.cs.cafe.controller;

import ku.cs.cafe.request.AddCartRequest;
import ku.cs.cafe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;


    @PostMapping("/{menuId}")
    public String order(@PathVariable UUID menuId,
                        @ModelAttribute AddCartRequest request, Model model){
        orderService.order(menuId, request);
        return "redirect:/menus";
    }
}

