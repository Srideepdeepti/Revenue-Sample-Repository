Feature: Author API should respond correctly

Scenario Outline: Validate Author API has given personal name and alternate names in response
    Given the baseURL
    When I hit the endpoint for getAuthors
    Then I verify the "<key_1>" in response is "<expected_personal_name>"
    And I verify the "<key_2>" in response contains "<expected_alternate_name>"

    Examples: 
      | key_1 | key_2| expected_personal_name |expected_alternate_name|
      |  personal_name|  alternate_names | Sachi Rautroy |Yugashrashta Sachi Routray|
     
