

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TestSequence")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestSeq{
    private List<? extends TestStep> testSeq;

    public TestSeq(){}
    public TestSeq(List<? extends TestStep> testSeq){
        super();
        this.testSeq = testSeq;
    }

    public List<? extends TestStep> getTestSeq(){
        return testSeq;
    }

    public void setTestSeq(List<? extends TestStep> testSeq){
        this.testSeq = testSeq;
    }

    public void printSeq() {
        for (TestStep testStep : testSeq)
        {
            System.out.println(testStep.toString());
        }
    }
}
