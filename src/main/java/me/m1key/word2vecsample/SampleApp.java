package me.m1key.word2vecsample;

import org.apache.commons.lang3.tuple.Pair;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.function.Consumer;

public class SampleApp {
    public static void main(String[] args) throws IOException {
        Path gModel = Paths.get("/home/mike/Downloads/GoogleNews-vectors-negative300.bin.gz");
        WordVectors vec = WordVectorSerializer.loadGoogleModel(gModel.toFile(), true);

        listen(p -> System.out.println(vec.wordsNearest(p.getLeft(), p.getRight(), 10)));
    }

    private static void listen(Consumer<Pair<Collection<String>, Collection<String>>> callback) {
        System.out.println("Ready");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            Collection<String> positive = new ArrayList<>();
            Collection<String> negative = new ArrayList<>();
            Collection<String> current = positive;
            String[] array = scanner.nextLine().split("(?=[-+])|(?<=[-+])");
            for (String str : array) {
                str = str.trim();
                switch (str) {
                    case "+":
                        current = positive;
                        break;
                    case "-":
                        current = negative;
                        break;
                    default:
                        current.add(str);
                        break;
                }
            }
            callback.accept(Pair.of(positive, negative));
        }
    }

}
