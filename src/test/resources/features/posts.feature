Feature: Post Tests
  Background:
    * url testUrl
    * def postsSchema = read("classpath:TestData/postSchema.json")

  @posts
  Scenario: Get all posts
    Given path 'posts'
    When method GET
    Then match responseStatus == 200
    And match each response == postsSchema

  @posts
  Scenario: Create new post
    Given path 'posts'
    And request { title : "This is my title", body: "Testing", userId: 2}
    When method POST
    Then match responseStatus == 201
    And match response == postsSchema

  @posts
  Scenario: Get a particular post
    Given path 'posts','10'
    When method GET
    Then match responseStatus == 200
    And match response == postsSchema
    And match response.title contains 'optio'


