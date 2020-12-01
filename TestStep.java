

public abstract class TestStep {
    private String testStep;

    public TestStep() {}
    public TestStep(String testStep){
        super();
        this.testStep = testStep;
    }

    public String getTestStep(){
           return testStep;
    }

    public void setTestStep(String testStep) {
        this.testStep = testStep;
    }

    @Override
    public String toString() {
        return "TestStep{" +
                "testStep='" + testStep + '\'' +
                '}';
    }
}
