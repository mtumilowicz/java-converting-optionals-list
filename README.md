[![Build Status](https://travis-ci.com/mtumilowicz/java-converting-optionals-list.svg?branch=master)](https://travis-ci.com/mtumilowicz/java-converting-optionals-list)

# java-converting-optionals-list
Three approaches to Optionals list conversion (using streams).

## prior java 9
* filter + map
    ```
    dataStream()
        .filter(Optional::isPresent)
        .map(Optional::get)
    ```
* flatMap
    ```
    dataStream()
        .flatMap(x -> x.map(Stream::of).orElseGet(Stream::empty))
    ```

## java 9
* flatMap + Optional as a stream
    ```
    dataStream()
        .flatMap(Optional::stream)
    ```
    
**Remark**: Internally `Optional::stream` is implemented:
```
public Stream<T> stream() {
    if (!isPresent()) {
        return Stream.empty();
    } else {
        return Stream.of(value);
    }
}    
```


## tests
Fully tested in `OptionalListConversionTest`.