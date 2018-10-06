import org.junit.Test;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2018-10-06.
 */
public class OptionalListConversionTest {
    
    @Test
    public void filterThenMap() {
        String collect = dataStream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.joining("-"));
        
        assertThat(collect, is("1-2-3-4-5"));
    }

    @Test
    public void flatMap() {
        String collect = dataStream()
                .flatMap(x -> x.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.joining("-"));

        assertThat(collect, is("1-2-3-4-5"));
    }

    @Test
    public void optionalAsStream() {
        String collect = dataStream()
                .flatMap(Optional::stream)
                .collect(Collectors.joining("-"));

        assertThat(collect, is("1-2-3-4-5"));
    }
    
    
    private Stream<Optional<String>> dataStream() {
        return Stream.of(
                Optional.of("1"), 
                Optional.of("2"), 
                Optional.of("3"),
                Optional.of("4"),
                Optional.of("5")
                );
    }
}
