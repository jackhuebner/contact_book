
package com.creightoncoders.contactbook;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    private final List<Contact> contacts = new ArrayList<>();

    private Long nextId = 1L;

    public WebController() {
        contacts.add(new Contact(nextId++, "Jack", "Smith"));
        contacts.add(new Contact(nextId++, "John", "Doe"));
        contacts.add(new Contact(nextId++, "Sarah", "Johnson"));
    }

    // Display all contacts
    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("contacts", contacts);

        return "home_page";
    }

    // Display the contact creation form
    @GetMapping("/add-contact")
    public String addContactForm(Model model) {

        model.addAttribute("contact", new Contact());
        model.addAttribute("formAction", "/contacts");
        model.addAttribute("formTitle", "Add a New Contact");
        model.addAttribute("buttonText", "Save Contact");

        return "contact_form";
    }

    // Save a new contact
    @PostMapping("/contacts")
    public String saveContact(@ModelAttribute Contact contact) {

        contact.setId(nextId++);
        contacts.add(contact);

        return "redirect:/";
    }

    // Display an individual contact
    @GetMapping("/contacts/{id}")
    public String viewContact(@PathVariable Long id, Model model) {

        Contact contact = findContact(id);

        if (contact == null) {
            return "redirect:/";
        }

        model.addAttribute("contact", contact);

        return "contact_details";
    }

    // Display the edit form
    @GetMapping("/contacts/{id}/edit")
    public String editContactForm(@PathVariable Long id, Model model) {

        Contact contact = findContact(id);

        if (contact == null) {
            return "redirect:/";
        }

        model.addAttribute("contact", contact);
        model.addAttribute("formAction", "/contacts/" + id);
        model.addAttribute("formTitle", "Edit Contact");
        model.addAttribute("buttonText", "Update Contact");

        return "contact_form";
    }

    // Update an existing contact
    @PostMapping("/contacts/{id}")
    public String updateContact(
            @PathVariable Long id,
            @ModelAttribute Contact updatedContact) {

        Contact existingContact = findContact(id);

        if (existingContact == null) {
            return "redirect:/";
        }

        existingContact.setFirstName(updatedContact.getFirstName());
        existingContact.setLastName(updatedContact.getLastName());
        existingContact.setPhoneNumber(updatedContact.getPhoneNumber());
        existingContact.setEmail(updatedContact.getEmail());
        existingContact.setAddress(updatedContact.getAddress());
        existingContact.setNotes(updatedContact.getNotes());

        return "redirect:/contacts/" + id;
    }

    // Delete a contact
    @PostMapping("/contacts/{id}/delete")
    public String deleteContact(@PathVariable Long id) {

        contacts.removeIf(contact -> contact.getId().equals(id));

        return "redirect:/";
    }

    // Find a contact using its ID
    private Contact findContact(Long id) {

        for (Contact contact : contacts) {
            if (contact.getId().equals(id)) {
                return contact;
            }
        }

        return null;
    }
}
