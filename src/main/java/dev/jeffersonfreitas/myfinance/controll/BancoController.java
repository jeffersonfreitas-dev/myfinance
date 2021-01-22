package dev.jeffersonfreitas.myfinance.controll;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bancos")
public class BancoController {
	
	@GetMapping
	public String test() {
		return "Hello world!";
	}

}
