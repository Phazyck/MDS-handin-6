
package xml.verification;

import javax.xml.bind.annotation.*;

/**
 * Java Class representation of the XML element <dcrgVerificationResult>.
 */
@XmlRootElement( name="dcrgVerificationResult")
public class DCRGVerificationResult extends xml.Serializable {
    @XmlAttribute(name="timestamp")
    public String timestamp;
    @XmlElement(name = "verificationTime")
    public String verificationTime;
    @XmlElement(name = "dcrgModel")
    public DCRGModel model;
    @XmlElement(name = "dcrgProperty")
    public DCRGModel property;
    @XmlElement(name = "dcrgComposite")
    public DCRGModel composite;
}
