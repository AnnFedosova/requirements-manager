package reportsgenerator;

import org.wickedsource.docxstamper.DocxStamper;
import org.wickedsource.docxstamper.DocxStamperConfiguration;

import java.io.*;

public class ReportGenerator {
    public byte[] template(SpecificationWithRequirements specificationWithRequirements) throws IOException {
        InputStream in = getClass().getResourceAsStream("SpecificationReport.docx");
        OutputStream out = new FileOutputStream(File.createTempFile(getClass().getSimpleName(), ".docx"));
        DocxStamper stamper = new DocxStamperConfiguration().setFailOnUnresolvedExpression(false).build();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        stamper.stamp(in, specificationWithRequirements,  outputStream);
        in.close();
        out.close();

        byte[] result = outputStream.toByteArray();
        return result;
    }


    public byte[] template(ReleaseWithRequirements releaseWithRequirements) throws IOException {
        InputStream in = getClass().getResourceAsStream("ReleaseReport.docx");
        OutputStream out = new FileOutputStream(File.createTempFile(getClass().getSimpleName(), ".docx"));
        DocxStamper stamper = new DocxStamperConfiguration().setFailOnUnresolvedExpression(false).build();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        stamper.stamp(in, releaseWithRequirements,  outputStream);
        in.close();
        out.close();

        byte[] result = outputStream.toByteArray();
        return result;
    }

    public byte[] template(TestPlanWithInfoForReport testPlanWithInfoForReport) throws IOException {
        InputStream in = getClass().getResourceAsStream("TestPlanReport.docx");
        OutputStream out = new FileOutputStream(File.createTempFile(getClass().getSimpleName(), ".docx"));
        DocxStamper stamper = new DocxStamperConfiguration().setFailOnUnresolvedExpression(false).build();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        stamper.stamp(in, testPlanWithInfoForReport,  outputStream);
        in.close();
        out.close();

        byte[] result = outputStream.toByteArray();
        return result;
    }

    public byte[] template(TestSuiteWithInfoForReport testSuiteWithInfoForReport) throws IOException {
        InputStream in = getClass().getResourceAsStream("TestSuiteReport.docx");
        OutputStream out = new FileOutputStream(File.createTempFile(getClass().getSimpleName(), ".docx"));
        DocxStamper stamper = new DocxStamperConfiguration().setFailOnUnresolvedExpression(false).build();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        stamper.stamp(in, testSuiteWithInfoForReport,  outputStream);
        in.close();
        out.close();

        byte[] result = outputStream.toByteArray();
        return result;
    }

}
