package annotations.database;
import java.lang.annotation.*;

@Target(ElementType.TYPE)      // Applies to classes only
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandType {
    String name() default "";
}
