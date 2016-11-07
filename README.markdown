# WORD2VEC SAMPLE

## Introduction

Sample app that explores the Word2Vec library. I got the initial version from Vladimir Parshikov.

## Links

Tutorial: https://deeplearning4j.org/word2vec#just

Sample data: https://s3.amazonaws.com/dl4j-distribution/GoogleNews-vectors-negative300.bin.gz

Maven repo: https://search.maven.org/#search%7Cga%7C1%7Cnd4j

## Known issues

### Memory error

Caused by: java.lang.OutOfMemoryError: Physical memory usage is too high (7590080512 > Pointer.maxPhysicalBytes)

Add this to run config in IntelliJ Idea:

Solution: -Xms1024m     -Xmx10g     -XX:MaxPermSize=2g