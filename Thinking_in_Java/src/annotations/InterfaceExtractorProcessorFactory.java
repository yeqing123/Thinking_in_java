package annotations;
import java.util.*;
import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;

public class InterfaceExtractorProcessorFactory 
    implements AnnotationProcessorFactory {
    public AnnotationProcessor getProcessorFor(
    		Set<AnnotationTypeDeclaration> atds,
    		AnnotationProcessorEnvironment env) {
    	return new InterfaceExtractorProcessor(env);
    }
    public Collection<String> supportedAnnotationTypes() {
    	return Collections.singleton("annotations.ExtractInterface");
    }
    public Collection<String> supportedOptions() {
    	return Collections.emptySet();
    }
}
