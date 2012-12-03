package xml;

import java.io.*;
import javax.xml.bind.*;

public class Serializer {

    /**
     * Serialize a given xml.Serializable into a String of XML.
     *
     * @param <T> The type of the object to be serialized, (must extend
     * xml.Serializable).
     * @param serializable The serializable object.
     * @return The serialized XML String.
     */
    public static <T extends xml.Serializable> String serialize(T serializable) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(serializable.getClass());
        StringWriter writer = new StringWriter();
        context.createMarshaller().marshal(serializable, writer);
        return writer.toString();
    }

    /**
     * De-serialize a String of XML into a Java object of a specified type.
     *
     * @param <T> The type (excetends xml.Serializable) of the object which the
     * String should be de-serialized into.
     * @param xml The String of XML.
     * @param target The target type the xml should be de-serialized into.
     * @return The de-serialized object.
     */
    public static <T extends xml.Serializable> T deSerialize(String xml, Class<T> target) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(target);
        return (T) context.createUnmarshaller().unmarshal(new StringReader(xml));
    }
}
