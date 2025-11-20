package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

class AppTest {

//  @Test
//  void testRunWithValidInputStylishFormat() {
//    // Mocking command-line arguments
//    String[] args = {
//        "src/test/resources/fixtures/file1.json",
//        "src/test/resources/fixtures/file2.json",
//        "--format=stylish"
//    };
//
//    // Capturing console output
//    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//    PrintStream originalOut = System.out;
//    System.setOut(new PrintStream(outputStream));
//
//    try {
//      // Run the application
//      App app = new App();
//      new CommandLine(app).execute(args);
//
//      // Validate output
//      String output = outputStream.toString();
//      assertTrue(output.contains("{")); // placeholder assertion
//    } finally {
//      // Reset console output
//      System.setOut(originalOut);
//    }
//  }
//
//  @Test
//  void testRunWithValidInputPlainFormat() {
//    // Mocking command-line arguments
//    String[] args = {
//        "src/test/resources/fixtures/file1.json",
//        "src/test/resources/fixtures/file2.json",
//        "--format=plain"
//    };
//
//    // Capturing console output
//    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//    PrintStream originalOut = System.out;
//    System.setOut(new PrintStream(outputStream));
//
//    try {
//      // Run the application
//      App app = new App();
//      new CommandLine(app).execute(args);
//
//      // Validate output
//      String output = outputStream.toString();
//      assertTrue(output.contains("Property")); // placeholder assertion
//    } finally {
//      // Reset console output
//      System.setOut(originalOut);
//    }
//  }
//
//  @Test
//  void testRunWithInvalidFilePath() {
//    // Mocking command-line arguments
//    String[] args = {"invalid-path-1.json", "invalid-path-2.json"};
//
//    // Capturing error output
//    ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
//    PrintStream originalErr = System.err;
//    System.setErr(new PrintStream(errorStream));
//
//    try {
//      // Run the application
//      App app = new App();
//      new CommandLine(app).execute(args);
//
//      // Validate error output
//      String output = errorStream.toString();
//      assertTrue(output.contains("Error")); // placeholder assertion
//    } finally {
//      // Reset error output
//      System.setErr(originalErr);
//    }
//  }
//
//  @Test
//  void testRunWithUnsupportedFormat() {
//    // Mocking command-line arguments
//    String[] args = {
//        "src/test/resources/fixtures/file1.json",
//        "src/test/resources/fixtures/file2.json",
//        "--format=unsupported"
//    };
//
//    // Capturing error output
//    ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
//    PrintStream originalErr = System.err;
//    System.setErr(new PrintStream(errorStream));
//
//    try {
//      // Run the application
//      App app = new App();
//      new CommandLine(app).execute(args);
//
//      // Validate error output
//      String output = errorStream.toString();
//      assertTrue(output.contains("Error")); // placeholder assertion
//    } finally {
//      // Reset error output
//      System.setErr(originalErr);
//    }
//  }
}