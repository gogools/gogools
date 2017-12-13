package gogools.test.restroom_mockup;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestroomController {

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/feedme")
	public Data greeting(@RequestParam(value = "data", defaultValue = "no data") String data) {
		return new Data(counter.incrementAndGet(), "Data acquired!");
	}
}