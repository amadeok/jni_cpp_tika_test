package src.main.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.extractor.EmbeddedDocumentExtractor;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
//import org.apache.tika.metadata.serialization.JsonMetadataList;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.EmptyParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.RecursiveParserWrapper;
import org.apache.tika.sax.BasicContentHandlerFactory;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ContentHandlerFactory;
import org.apache.tika.sax.RecursiveParserWrapperHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.ContentHandler;
import java.time.Instant;
import java.io.File;
import java.util.*; 

public class MyTest {

    public static String parseExample(String file)
            throws IOException, SAXException, TikaException {

        AutoDetectParser parser = new AutoDetectParser();

        BodyContentHandler handler = new BodyContentHandler(-1);

        Metadata metadata = new Metadata();

        InputStream stream = new FileInputStream(file);

        parser.parse(stream, handler, metadata);

        String str = handler.toString();

        return str;
    }
   

    public static String tika_parse(String input_file) { // <== add this new function
        String s = "";
        try {
             s = parseExample(input_file);
        } catch (Exception e) {
            System.out.println("Something went wrong. \n" + e);
        }
        return s;
    }
}