Feature: Note api validation

  @createNote @E2E
  Scenario Outline: create note
    Given add note payload "<title>" "<description>" "<category>"
    When user call "noteAPI" with "POST" request
    Then APi call get success with "200" code
    And "success" in response should be "true"
    And "message" in response should be "Note successfully created"
    And save note id

    Examples:
    |title|description|category|
    |self help|don't overthink, just believe in action|Home|

  @E2E
  Scenario Outline: get note
    Given add token in header
    When user call "noteAPI" with "GET" request
    Then APi call get success with "200" code
    And "success" in response should be "true"
    And "message" in response should be "Note successfully retrieved"
    And verify the note details "<title>" "<description>" "<category>"

  Examples:
    |title|description|category|
    |self help|don't overthink, just believe in action|Home|

  @E2E
  Scenario: delete note
    Given add token in header
    When user call "noteAPI" with "DELETE" request
    Then APi call get success with "200" code
    And "success" in response should be "true"
    And "message" in response should be "Note successfully deleted"

