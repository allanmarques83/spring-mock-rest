package io.github.allanmarques83;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Produces multiple mocks for REST controllers and REST clients.
 * Attention: the return type of your method must always be a ResponseEntity<T>
 * 
 * @param mockId specify any ID for that mock and pass, as one of the function arguments, a String with that variable name.
 * @param response the path of the .json file in the spring project's resource folder.
 * @param statusCode the http status code to be returned by the mock. Default: 200
 * @author Allan Marques
 * 
 * <p>Example:</p>
 * <pre class="code">
 *&#64;MockRestValues({
 *  &#64;MockValue(mockId="123456", response="some_file1.json", statusCode=400)
 *  &#64;MockValue(mockId="654321", response="some_file2.json", statusCode=401)
 *})
 *public ResponseEntity&lt;String&gt; someMethod(@RequestParam(required=false) mockId) {
 *  return ResponseEntity.ok("test");
 *}
 * </pre>
 * 
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MockRestValues {
    MockValue[] value();
}