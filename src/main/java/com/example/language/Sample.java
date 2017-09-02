package com.example.language;

// [START language_quickstart]
// Imports the Google Cloud client library

import com.google.cloud.language.v1beta2.*;

public class Sample {

  public static void main(String... args) throws Exception {

    // Instantiates a client
    LanguageServiceClient languageApi = LanguageServiceClient.create();

    // The text to analyze
    String text = "Google, headquartered in Mountain View, unveiled the new Android phone at the Consumer Electronic " +
            "Show.  Sundar Pichai said in his keynote that users love their new Android phones.";

    Document doc = Document.newBuilder()
            .setContent(text).setType(Document.Type.PLAIN_TEXT).build();

    // Sentiment
    analyseSentiment(languageApi, doc);

    //Entity Sentiment
    analyseEntity(languageApi, doc);

    //Syntax Analysis
    analyseSyntax(languageApi, doc);
  }

  /**
   * Sample method to get the overall sentiment score
   * @param languageApi
   * @param doc
   */
  public static void analyseSentiment(LanguageServiceClient languageApi, Document doc) {

    // Detects the sentiment of the text
    Sentiment sentiment = languageApi.analyzeSentiment(doc).getDocumentSentiment();

    System.out.println(" <------ GCP NLP Sentiment Analysis -----> ");

    System.out.println();
    System.out.println("Overall Score   : " + sentiment.getScore());
    System.out.println();

  }

  /**
   * Sample method to analyse the entities with sentiment in a text.
   * @param languageApi
   * @param doc
   */
  public static void analyseEntity(LanguageServiceClient languageApi, Document doc) {

    AnalyzeEntitySentimentRequest request = AnalyzeEntitySentimentRequest.newBuilder()
            .setDocument(doc)
            .setEncodingType(EncodingType.UTF16).build();
    AnalyzeEntitySentimentResponse response = languageApi.analyzeEntitySentiment(request);

    System.out.println(" <------ GCP NLP Entity Sentiment Analysis -----> ");

    for(Entity entity : response.getEntitiesList())
    {
      System.out.println();
      System.out.println("Name      : " + entity.getName());
      System.out.println("Type      : " + entity.getType().name());
      System.out.println("Score     : " + entity.getSentimentOrBuilder().getScore());
      System.out.println("Salience  : " + entity.getSentimentOrBuilder().getScore());
      System.out.println();
    }
  }

  /**
   * Sample method to analyse syntax.
   * @param languageApi
   * @param doc
   */
  public static void analyseSyntax(LanguageServiceClient languageApi, Document doc) {

    AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.newBuilder()
            .setDocument(doc)
            .setEncodingType(EncodingType.UTF16).build();
    AnalyzeSyntaxResponse response = languageApi.analyzeSyntax(request);

    System.out.println(" <------ GCP NLP Syntax Analysis -----> ");

    for(Token token : response.getTokensList())
    {
      System.out.println();
      System.out.println(token.toString());
      System.out.println();
    }

  }
}
// [END language_quickstart]
