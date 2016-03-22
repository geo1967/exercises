package webservice;


import calculators.PrimeCalculatorFactory;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class PrimesController {
    
    
        private PrimeCalculatorFactory factory = new PrimeCalculatorFactory();
        private Converter converter = new Converter();
    
    
    
	private final static Logger log = LoggerFactory.getLogger(PrimesController.class.getName());;


    @RequestMapping(value="/prime/{algo}/{max_limit}",method = RequestMethod.GET)
    @ResponseBody
    String getPrimes(@PathVariable String algo, @PathVariable int max_limit) {
    	log.debug("rest method <prime>");
            int[] res = factory.getCalculator(algo).primes(max_limit);
        return converter.serialize(res);
    }

    
    @RequestMapping("/quit")
    @ResponseBody
    void quit() {
    	log.debug("rest method <quit>");
        System.exit(-999);
    }

   
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String handleException(Exception exception)
	{
		return exception.getMessage();
	}
    
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(PrimesController.class, args);
    }
    
    
    
    
}
