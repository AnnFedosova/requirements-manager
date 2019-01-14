package reportsgenerator;

import org.wickedsource.docxstamper.DocxStamper;
import org.wickedsource.docxstamper.DocxStamperConfiguration;

import java.io.*;

public class ReportGenerator {
    public void template(SpecificationWithRequirements specificationWithRequirements) throws IOException {
        InputStream in = getClass().getResourceAsStream("SpecificationReport.docx");
        OutputStream out = new FileOutputStream(File.createTempFile(getClass().getSimpleName(), ".docx"));

        DocxStamper stamper = new DocxStamperConfiguration().setFailOnUnresolvedExpression(false).build();

        stamper.stamp(in, specificationWithRequirements,  out);
        in.close();
        out.close();
    }
}
