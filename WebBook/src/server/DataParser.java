package server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;
import model.PersonAddress;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Collection;

public class DataParser{

    private static final String ROOT_ELEM_TAG = "root";
    private static Document document;

    private static Document createDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document document = null;
        try {
            document = factory.newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static void save(File file, Collection<Person> elements) {
        document = createDocument();

        Element root = document.createElement("root");
        document.appendChild(root);

        Element addressBook = document.createElement("AddressBook");
        root.appendChild(addressBook);

        for (Person person : elements) {
            Element personElement = document.createElement("person");
            personElement.setAttribute("firstName", person.getFirstName());
            personElement.setAttribute("lastName", person.getLastName());
            personElement.setAttribute("surName", person.getSurName());

            Element addressElement = document.createElement("personAddress");
            addressElement.setAttribute("country", person.getAddress().getCountry());
            addressElement.setAttribute("region", person.getAddress().getRegion());
            addressElement.setAttribute("city", person.getAddress().getCity());
            addressElement.setAttribute("street", person.getAddress().getStreet());
            addressElement.setAttribute("houseNumber", String.valueOf(person.getAddress().getHouseNumber()));
            addressElement.setAttribute("pavilionNumber", String.valueOf(person.getAddress().getPavilionNumber()));
            addressElement.setAttribute("flatNumber", String.valueOf(person.getAddress().getFlatNumber()));

            personElement.appendChild(addressElement);
            addressBook.appendChild(personElement);
        }

        try {
            toXML(file, document);
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void toXML(File file, Document doc) throws TransformerException, IOException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(document), new StreamResult(file));
    }

    public static ObservableList<Person> loadAddressBook(File fileName) {
        InputStream in = null;
        try {
            in = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XMLGraphLoader loader = new XMLGraphLoader(in);
        try {
            loader.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<Person> loadAddressBook;
        loadAddressBook = loader.getPersons();

        return loadAddressBook;
    }

    private static class XMLGraphLoader {
        private InputSource source;
        private SAXParser parser;
        private DefaultHandler documentHandler;

        private ObservableList<Person>  personsList = FXCollections.observableArrayList();

        public XMLGraphLoader(InputStream stream) {
            try {
                Reader reader = new InputStreamReader(stream);
                source = new InputSource(reader);
                parser = SAXParserFactory.newInstance().newSAXParser();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            documentHandler = new XMLParser();
        }

        public void parse() throws Exception {
            parser.parse(source, documentHandler);
        }

        public ObservableList<Person> getPersons() {
            return personsList;
        }

        class XMLParser extends DefaultHandler {

            PersonAddress tempPersonAddress = null;
            Person tempPerson = null;

            String firstName = null;
            String secondName = null;
            String surName = null;

            public void startElement(String uri, String localName, String qName, Attributes attr) {

                switch (qName){
                    case ("person"):
                        parsePerson(attr);
                        break;
                    case ("personAddress"):
                        parsePersonAddress(attr);
                        break;
                    }

                if(firstName != null && tempPersonAddress != null){

                    tempPerson = new Person(firstName, secondName, surName, tempPersonAddress);
                    personsList.add(tempPerson);

                    firstName = null;
                    secondName = null;
                    surName = null;
                    tempPersonAddress = null;
                }
            }

            private void parsePersonAddress(Attributes attr){
                tempPersonAddress = new PersonAddress(
                        attr.getValue("country"),
                        attr.getValue("region"),
                        attr.getValue("city"),
                        attr.getValue("street"),
                        Integer.parseInt(attr.getValue("houseNumber")),
                        Integer.parseInt(attr.getValue("pavilionNumber")),
                        Integer.parseInt(attr.getValue("flatNumber"))
                );
            }

            private void parsePerson(Attributes attr) {
                firstName = attr.getValue("firstName");
                secondName = attr.getValue("lastName");
                surName = attr.getValue("surName");
            }
        }
    }
}


