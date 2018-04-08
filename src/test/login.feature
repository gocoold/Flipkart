Feature : Visit create page

Given I am an authorised user of flipkart
And I enter the valid flipkart url

  @test 1
  Scenario: Flipkart login success
    When I login with a valid credentials
    Then I should be able to login to the application
    And I should reach the home page of flipkart

  @test 2
  Scenario: Flipkart login failure with invalid username
    When I login with an invalid Email ID/Mobile number
    Then I should not be able to login to the application
    And I get an error 'Please enter valid Email ID/Mobile number'

  @test 3
  Scenario: Flipkart login failure with invalid password
    When I login with an invalid password
    Then I should not be able to login to the application
    And I get an error 'Your username or password is incorrect'

  @test 4
  Scenario: Flipkart login failure when no username provided
    When I login without providing an Email ID/Mobile number
    Then I should not be able to login to the application
    And I get an error 'Please enter valid Email ID/Mobile number'

  @test 5
  Scenario: Flipkart login failure when no password provided
    When I login without providing an Email ID/Mobile number
    Then I should not be able to login to the application
    And I get an error 'Please enter Password'

  @test 6
  Scenario: Flipkart login failure when no username and password provided
    When I login without providing login credentials
    Then I should not be able to login to the application
    And I get an error 'Please enter valid Email ID/Mobile number