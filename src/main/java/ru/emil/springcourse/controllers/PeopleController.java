package ru.emil.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.emil.springcourse.dao.PersonDAO;
import ru.emil.springcourse.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonDAO personDAO;
    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.getPeople());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping
    public String create(@RequestParam("name") String name, @RequestParam("surname") String surname,
                         @RequestParam("email") String email, Model model){
        Person person = new Person();

        person.setName(name);
        person.setSurname(surname);
        person.setEmail(email);

        model.addAttribute(person);
        personDAO.save(person);

        return "redirect:/people";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model){
        personDAO.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        personDAO.update(id,person);
        return "redirect:/people";
    }

}
