package io.github.allanmarques83;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * Produces a mock for a given REST controller and REST client.
 * 
 * @param mockId specify any ID for that mock and pass, as one of the function arguments, a String with that variable name.
 * @param response the path of the .json file in the spring project's resource folder.
 * @param statusCode the http status code to be returned by the mock. Default: 200
 * @author Allan Marques
 * 
 * <p>Example:</p>
 * <pre class="code">
 * &#64;MockRest(mockId="123456", response="some_file.json", statusCode=400)
 *public ResponseEntity&lt;String&gt; someMethod(@RequestParam(required=false) mockId) {
 *  return ResponseEntity.ok("test");
 *}
 * </pre>
 * 
*/
public @interface MockRest {
    String mockId();
    String response();
    int statusCode() default 200;
}