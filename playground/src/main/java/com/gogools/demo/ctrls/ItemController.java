package com.gogools.demo.ctrls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gogools.demo.entities.Item;
import com.gogools.demo.repositories.ReadingItemRepository;

@Controller
@RequestMapping("/")
public class ItemController {
	
	private ReadingItemRepository readingItemRepository;
	
	@Autowired
	public ItemController(ReadingItemRepository readingItemRepository) {
		
		this.readingItemRepository = readingItemRepository;
	}
	
	@RequestMapping(value = "/{itemName}", method = RequestMethod.GET)
	public String readersItems(@PathVariable("reader") String name, Model model) {
		
		List<Item> itemList = readingItemRepository.findByName(name);
		
		if (itemList != null) {
			
			model.addAttribute("items", itemList);
		}
		return "itemList";
	}
	
	@RequestMapping(value = "/{itemName}", method = RequestMethod.POST)
	public String addToReadingList(@PathVariable("reader") String name, Item item) {
		
		item.setName(name);
		readingItemRepository.save(item);
		return "redirect:/{itemName}";
	}
}
