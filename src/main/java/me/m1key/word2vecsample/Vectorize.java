package me.m1key.word2vecsample;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CustomStemmingPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.StemmingPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.nd4j.linalg.io.ClassPathResource;
import org.tartarus.snowball.ext.EnglishStemmer;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class Vectorize {
        public static void main(String[] args) throws IOException {
            SentenceIterator iter = new LineSentenceIterator(new ClassPathResource("buttocks.txt").getFile());
            iter.setPreProcessor((SentencePreProcessor) sentence -> sentence.toLowerCase());
            TokenizerFactory t = new DefaultTokenizerFactory();
//            t.setTokenPreProcessor(new CommonPreprocessor());
//            t.setTokenPreProcessor(new StemmingPreprocessor());
            t.setTokenPreProcessor(new CustomStemmingPreprocessor(new EnglishStemmer()));

            Word2Vec vec = new Word2Vec.Builder()
                    .minWordFrequency(1)
                    .iterations(2)
                    .layerSize(100)
                    .seed(42)
                    .windowSize(15)
                    .iterate(iter)
                    .tokenizerFactory(t)
                    .build();

            vec.fit();

            WordVectorSerializer.writeWordVectors(vec, "pathToWriteto.txt");
            WordVectorSerializer.writeFullModel(vec, "pathToSaveModel.txt");

            Collection<String> lst = vec.wordsNearest("buttock", 10);
            System.out.println(lst);

            Word2Vec word2Vec = WordVectorSerializer.loadFullModel("pathToSaveModel.txt");

            lst = word2Vec.wordsNearest("buttock", 10);
            System.out.println(lst);
        }
}
