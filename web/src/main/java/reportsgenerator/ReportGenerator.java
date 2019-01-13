package reportsgenerator;

import dto.SpecificationDTO;
import org.wickedsource.docxstamper.DocxStamper;
import org.wickedsource.docxstamper.DocxStamperConfiguration;

import java.io.*;

public class ReportGenerator {
    public void template(SpecificationDTO specification) throws IOException {
        InputStream in = getClass().getResourceAsStream("SpecificationReport.docx");
        OutputStream out = new FileOutputStream(File.createTempFile(getClass().getSimpleName(), ".docx"));
//        SpecificationContext context = new SpecificationContext(specification);

        DocxStamper stamper = new DocxStamperConfiguration()
                .setFailOnUnresolvedExpression(false)
                .build();

        stamper.stamp(in, specification, out);
        in.close();
        out.close();
    }
}
