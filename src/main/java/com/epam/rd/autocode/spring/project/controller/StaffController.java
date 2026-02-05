package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.request.book.BookReq;
import com.epam.rd.autocode.spring.project.dto.response.book.BookFullResp;
import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.model.enums.Language;
import com.epam.rd.autocode.spring.project.model.enums.OrderStatus;
import com.epam.rd.autocode.spring.project.service.BookService;
import com.epam.rd.autocode.spring.project.service.EmployeeService;
import com.epam.rd.autocode.spring.project.service.OrderService;
import com.epam.rd.autocode.spring.project.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/staff")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
public class StaffController {

    private final BookService bookService;
    private final UserService userService;
    private final OrderService orderService;
    private final EmployeeService employeeService;

    @GetMapping
    public String staffDashboard(Model model,
                                 @RequestParam(defaultValue = "0") int bookPage,
                                 @RequestParam(defaultValue = "0") int orderPage,
                                 @RequestParam(defaultValue = "0") int userPage) {

        model.addAttribute("booksPage", bookService.getAllBooks(PageRequest.of(bookPage, 5)));
        model.addAttribute("ordersPage", orderService.getAllOrders(PageRequest.of(orderPage, 5)));
        model.addAttribute("usersPage", userService.getAllUsersWithStaff(PageRequest.of(userPage, 5)));

        return "staff-dashboard";
    }

    @PostMapping("/users/block")
    public String blockUser(@RequestParam String email) {
        employeeService.blockUserByEmail(email);
        return "redirect:/staff";
    }

    @PostMapping("/users/unblock")
    public String unblockUser(@RequestParam String email) {
        employeeService.unBlockUserByEmail(email);
        return "redirect:/staff";
    }

    @PostMapping("/orders/change-status")
    public String changeOrderStatus(@RequestParam Long orderId, @RequestParam OrderStatus status) {
        orderService.changedStatus(orderId, status);
        return "redirect:/staff";
    }


    @PostMapping("/books/delete")
    public String deleteBook(@RequestParam String name) {
        bookService.deleteBookByName(name);
        return "redirect:/staff";
    }

    @GetMapping("/books/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new BookReq());
        model.addAttribute("ageGroups", AgeGroup.values());
        model.addAttribute("languages", Language.values());
        return "add-book"; // Название HTML файла
    }

    @PostMapping("/books/add")
    public String processAddBook(@ModelAttribute("book") @Valid BookReq bookReq,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("ageGroups", AgeGroup.values());
            model.addAttribute("languages", Language.values());
            return "add-book";
        }
        bookService.addBook(bookReq);
        ra.addFlashAttribute("successMessage", "Book added successfully!");
        return "redirect:/staff";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        BookFullResp book = bookService.getBookFull(id);

        model.addAttribute("book", book);
        model.addAttribute("bookId", id); // Используем ID для формы
        model.addAttribute("ageGroups", AgeGroup.values());
        model.addAttribute("languages", Language.values());
        return "edit-book";
    }

    @PostMapping("/books/edit/{id}")
    public String processUpdateBook(@PathVariable Long id,
                                    @ModelAttribute("book") @Valid BookReq bookReq,
                                    BindingResult result,
                                    Model model,
                                    RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("ageGroups", AgeGroup.values());
            model.addAttribute("languages", Language.values());
            model.addAttribute("bookId", id);
            return "edit-book";
        }

        bookService.updateBookById(id, bookReq);

        ra.addFlashAttribute("successMessage", "Книга успешно обновлена!");
        return "redirect:/staff";
    }
}