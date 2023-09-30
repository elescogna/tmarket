# csc207-project

## 1. The problem domain
The problem domain we would like to explore is student-centered e-commerce. When in need to buy a bike or a bookshelf, or to sell a mini fridge after graduating, students always look for convenient options near campus. However, unless they personally know their client or seller, finding the right person to conclude a deal can be quite complicated. TmarkeTplace aims to takcle this problem.

## 2. Our application
TmarkeTplace is a platform where students can post announcements of what they would like to sell and find interesting deals whenever they need to buy something. The concept is similar to that od Ebay - but by students, for students!

## 3. The API
[a link for the documentation of the API we are using] \
[a screenshot of an attempt to try to use the API]
Source code of calling the API from Java:
```agsl
import java.io.IOException;
import okhttp3.*;

public class Main {
  public static String callAPI() throws IOException {
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    final String API_KEY = System.getenv("API_KEY");

    Request
        request = new Request.Builder().url(String.format(
            "https://maps.googleapis.com/maps/api/distancematrix/json?destinations=New%%20York%%20City%%2C%%20NY&origins=Washington%%2C%%20DC%%7CBoston&units=imperial&key=%s",
            API_KEY))
        .method("GET", null)
            .addHeader("Content-Type", "application/json")
            .build();

    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }

  public static void main(String[] args) throws IOException {
    System.out.println(callAPI());
  }
}
```
Example output of running our Java code:
```
{
   "destination_addresses" : 
   [
      "New York, NY, USA"
   ],
   "origin_addresses" : 
   [
      "Washington, DC, USA",
      "Boston, MA, USA"
   ],
   "rows" : 
   [
      {
         "elements" : 
         [
            {
               "distance" : 
               {
                  "text" : "225 mi",
                  "value" : 361721
               },
               "duration" : 
               {
                  "text" : "3 hours 51 mins",
                  "value" : 13854
               },
               "status" : "OK"
            }
         ]
      },
      {
         "elements" : 
         [
            {
               "distance" : 
               {
                  "text" : "215 mi",
                  "value" : 346761
               },
               "duration" : 
               {
                  "text" : "3 hours 45 mins",
                  "value" : 13483
               },
               "status" : "OK"
            }
         ]
      }
   ],
   "status" : "OK"
}
```
## 4. Foreseen challenges
* Very few API calls will be needed to implement this project, which might not be in line with the expectations of the course.
* Having the framework to manage payment will be challenging, and we might not be able to implement this use case within the project timeframe

[add other foreseen challenges here]
