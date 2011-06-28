/**
 * 
 */
package cn.caz.elen.core;

import org.springframework.stereotype.Component;

/**
 * @author Cantoraz Zhou
 * 
 */
@Component
public class HelloWorld {

	public String sayHello() {
		return "Hello Maven";
	}

	public static void main(String[] args) {
		System.out.println(new HelloWorld().sayHello());
	}

}
