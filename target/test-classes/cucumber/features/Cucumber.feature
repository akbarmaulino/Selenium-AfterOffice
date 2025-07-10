Feature: Is It Friday Yet?
    Everybody wants to know if it is Friday yet.

    Scenario: Sunday isn't Friday
        Given today is "Sunday"
        When I ask if it is Friday yet
        # Then the answer should be "Yes"
        Then the answer should be "No"