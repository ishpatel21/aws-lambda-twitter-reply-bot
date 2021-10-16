# AWS Lambda Twitter Reply Bot

This project demostrate how to use aws lambda function with spring boot to reply "$DOGE" to Elon Musk's latest tweets.
We are using Twitter4J libraby for Twitter API (https://twitter4j.org/en/index.html).


# How to Build / Run in local
  - mvn clean install
  - ./mvnw spring-boot:run 
  - Make GET Call using PostMan http://localhost:8090/twitter/reply/str

# How to run on AWS Lambda
 1. Follow "Create Lambda Function via Management Console" in article https://www.baeldung.com/java-aws-lambda
 2. Create Api Gateway for endpoint https://docs.aws.amazon.com/apigateway/latest/developerguide/http-api-develop.html
 3. Then, use CloudWatch to invoke function, Follow Step 2 and 3 in article https://docs.aws.amazon.com/AmazonCloudWatch/latest/events/RunLambdaSchedule.html
    
