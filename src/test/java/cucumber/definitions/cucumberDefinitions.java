package cucumber.definitions;

import io.cucumber.java.en.*;

public class cucumberDefinitions {
    private String today;
    private String answer;

    @Given("today is {string}")
    public void todayIs(String day) {
        this.today = day;
    }
    @When("I ask if it is Friday yet")
    public void iAskIfItIsFridayYet() {
        if ("Friday".equalsIgnoreCase(today)) {
            answer = "Yes";
        } else {
            answer = "No";
        }
    }
    @Then("the answer should be {string}")
    public void theAnswerShouldBe(String expectedAnswer) {
        if (!expectedAnswer.equals(answer)) {
            throw new AssertionError("Expected answer: " + expectedAnswer + ", but got: " + answer);
        }
    }

    
}