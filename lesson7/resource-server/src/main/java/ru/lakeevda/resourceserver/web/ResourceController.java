
package ru.lakeevda.resourceserver.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@GetMapping("/resources")
	public String[] getMessages() {
		return new String[] {"resources 1", "resources 2", "resources 3"};
	}
}
