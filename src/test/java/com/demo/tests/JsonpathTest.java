package com.demo.tests;

import base.ApiBase;
import com.jayway.jsonpath.JsonPath;
import org.testng.annotations.Test;

import java.util.List;

@Test
    public class JsonpathTest extends ApiBase {
    @Test
        public static void jsonPathTest() {
            String json = """
                    {
                      "store": {
                        "book": [
                          {
                            "category": "reference",
                            "author": "Nigel Rees",
                            "title": "Sayings of the Century",
                            "price": 8.95
                          },
                          {
                            "category": "fiction",
                            "author": "Evelyn Waugh",
                            "title": "Sword of Honour",
                            "price": 12.99
                          },
                          {
                            "category": "fiction",
                            "author": "Herman Melville",
                            "title": "Moby Dick",
                            "isbn": "0-553-21311-3",
                            "price": 8.99
                          },
                          {
                            "category": "fiction",
                            "author": "J. R. R. Tolkien",
                            "title": "The Lord of the Rings",
                            "isbn": "0-395-19395-8",
                            "price": 22.99
                          }
                        ],
                        "bicycle": {
                          "color": "red",
                          "price": 19.95
                        }
                      }
                    }
                    
                    """;

            //all book titles
            List<String> titles = JsonPath.read(json, "$.store.book[*].title");
            System.out.println("Book Titles: " + titles);

            //price of the bicycle
            double bicyclePrice = JsonPath.read(json, "$.store.bicycle.price");
            System.out.println("Bicycle Price: " + bicyclePrice);

            //books with price less than 10
            List<String> cheapBooks = JsonPath.read(json, "$.store.book[?(@.price < 10)].title");
            System.out.println("Cheap Books: " + cheapBooks);

            //  authors of fiction books
            List<String> fictionAuthors = JsonPath.read(json, "$.store.book[?(@.category == 'fiction')].author");
            System.out.println("Fiction Authors: " + fictionAuthors);

            // books that have ISBN numbers
            List<String> isbnBooks = JsonPath.read(json, "$.store.book[?(@.isbn)].title");
            System.out.println("Books with ISBN: " + isbnBooks);
        }

}
