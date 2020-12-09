

//Written By....William Amaechi.
//Date..........11/13/2020
//Version.......1.10


import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

final class XMLCoder {
        private XMLCoder() {
            throw new UnsupportedOperationException();
        }
    public static void serialBeanToXML(TestSeq testSeq) {

        try {

            //creating the JAXB context
            JAXBContext beanContext = JAXBContext.newInstance(testSeq.getClass());
            //creating the marshaller object
            Marshaller marshallBean = beanContext.createMarshaller();
            //setting the file to show XML format
            marshallBean.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Store XML to File
            File beanFile = new File(testSeq.getClass().getSimpleName() + ".xml");
            //calling the marshall method
            marshallBean.marshal(testSeq, beanFile);
            marshallBean.marshal(testSeq, System.out);

        } catch (JAXBException exception) {
            exception.printStackTrace();
        }

    }

    public static void deSerialXMLToBean(File beanFile) {
        try {
            //creating th JAXB context
            JAXBContext beanContext = JAXBContext.newInstance(beanFile.getClass());
            //creating the unmarshall object
            Unmarshaller unmarshallBean = beanContext.createUnmarshaller();
            TestSeq bean = (TestSeq) unmarshallBean.unmarshal(beanFile);
            bean.printSeq();
        } catch (JAXBException exception) {
            exception.printStackTrace();
        }
    }
}
