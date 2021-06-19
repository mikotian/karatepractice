Feature: Post Tests
  Background:
    * url testUrl
    * def postsSchema = read("classpath:TestData/postSchema.json")

  @posts
  Scenario: Get a post
    Given path 'posts','1'
    When method GET
    Then match responseStatus == 200
    And match response == postsSchema
    * def pbody = response.body

  @posts
  Scenario: Patch a post
    Given path 'posts','1'
    And request { body: 'This is the patched body' }
    When method PATCH
    Then match responseStatus == 200
    And match response == postsSchema
    And match response.body == 'This is the patched body'


