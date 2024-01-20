Feature: User Api validation

  @registration @user
  Scenario: registration of a user
    Given add user payload for "register"
    When user call "registerAPI" with "POST" request
    Then APi call get success with "201" code
    And "success" in response should be "true"
    And "message" in response should be "User account created successfully"

  @login @Regression @user
  Scenario: login of a user
    Given add user payload for "login"
    When user call "logInAPI" with "POST" request
    Then APi call get success with "200" code
    And "success" in response should be "true"
    And "message" in response should be "Login successful"
    Then save the token

  @Regression @user
  Scenario: fetch user profile
    Given add token in header
    When user call "userProfileAPI" with "GET" request
    Then APi call get success with "200" code
    And "success" in response should be "true"
    And "message" in response should be "Profile successful"
    And verify the details "name" "email"

  @Regression @user
  Scenario: update user profile
    Given add user payload for "update"
    When user call "userProfileAPI" with "PATCH" request
    Then APi call get success with "200" code
    And "success" in response should be "true"
    And "message" in response should be "Profile updated successful"
    And verify the details "phone" "company"

  @deleteAccount
  Scenario: delete user account
    Given add token in header
    When user call "delteAccoutAPI" with "DELETE" request
    Then APi call get success with "200" code
    And "success" in response should be "true"
    And "message" in response should be "Account successfully deleted"

