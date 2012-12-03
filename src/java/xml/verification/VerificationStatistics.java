
package xml.verification;

import javax.xml.bind.annotation.*;

/**
 * Java Class representation of the XML element <verificationStatistics>. 
 */
@XmlRootElement(name="verificationStatistics")
public class VerificationStatistics extends xml.Serializable {
    @XmlElement(name="totalStateCount")
    public int total;
    @XmlElement(name="reachableStateCount")
    public int reachable;
    @XmlElement(name="transitionsCount")
    public int transitions;
    @XmlElement(name="averageStateMemory")
    public String avgStateMem;
    @XmlElement(name="totalStateMemory")
    public String totalStateMem;
    @XmlElement(name="totalProgramMemory")
    public String totalProgramMem;

}
